package net.minecraft.potion;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Effect {
   private final Map<Attribute, AttributeModifier> attributeModifierMap = Maps.newHashMap();
   private final EffectType type;
   private final int liquidColor;
   @Nullable
   private String name;

   @Nullable
   public static Effect get(int potionID) {
      return Registry.EFFECTS.getByValue(potionID);
   }

   public static int getId(Effect potionIn) {
      return Registry.EFFECTS.getId(potionIn);
   }

   protected Effect(EffectType typeIn, int liquidColorIn) {
      this.type = typeIn;
      this.liquidColor = liquidColorIn;
   }

   public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
      if (this == Effects.REGENERATION) {
         if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth()) {
            entityLivingBaseIn.heal(1.0F);
         }
      } else if (this == Effects.POISON) {
         if (entityLivingBaseIn.getHealth() > 1.0F) {
            entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, 1.0F);
         }
      } else if (this == Effects.WITHER) {
         entityLivingBaseIn.attackEntityFrom(DamageSource.WITHER, 1.0F);
      } else if (this == Effects.HUNGER && entityLivingBaseIn instanceof PlayerEntity) {
         ((PlayerEntity)entityLivingBaseIn).addExhaustion(0.005F * (float)(amplifier + 1));
      } else if (this == Effects.SATURATION && entityLivingBaseIn instanceof PlayerEntity) {
         if (!entityLivingBaseIn.world.isRemote) {
            ((PlayerEntity)entityLivingBaseIn).getFoodStats().addStats(amplifier + 1, 1.0F);
         }
      } else if ((this != Effects.INSTANT_HEALTH || entityLivingBaseIn.isEntityUndead()) && (this != Effects.INSTANT_DAMAGE || !entityLivingBaseIn.isEntityUndead())) {
         if (this == Effects.INSTANT_DAMAGE && !entityLivingBaseIn.isEntityUndead() || this == Effects.INSTANT_HEALTH && entityLivingBaseIn.isEntityUndead()) {
            entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, (float)(6 << amplifier));
         }
      } else {
         entityLivingBaseIn.heal((float)Math.max(4 << amplifier, 0));
      }

   }

   public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) {
      if ((this != Effects.INSTANT_HEALTH || entityLivingBaseIn.isEntityUndead()) && (this != Effects.INSTANT_DAMAGE || !entityLivingBaseIn.isEntityUndead())) {
         if (this == Effects.INSTANT_DAMAGE && !entityLivingBaseIn.isEntityUndead() || this == Effects.INSTANT_HEALTH && entityLivingBaseIn.isEntityUndead()) {
            int j = (int)(health * (double)(6 << amplifier) + 0.5D);
            if (source == null) {
               entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, (float)j);
            } else {
               entityLivingBaseIn.attackEntityFrom(DamageSource.causeIndirectMagicDamage(source, indirectSource), (float)j);
            }
         } else {
            this.performEffect(entityLivingBaseIn, amplifier);
         }
      } else {
         int i = (int)(health * (double)(4 << amplifier) + 0.5D);
         entityLivingBaseIn.heal((float)i);
      }

   }

   public boolean isReady(int duration, int amplifier) {
      if (this == Effects.REGENERATION) {
         int k = 50 >> amplifier;
         if (k > 0) {
            return duration % k == 0;
         } else {
            return true;
         }
      } else if (this == Effects.POISON) {
         int j = 25 >> amplifier;
         if (j > 0) {
            return duration % j == 0;
         } else {
            return true;
         }
      } else if (this == Effects.WITHER) {
         int i = 40 >> amplifier;
         if (i > 0) {
            return duration % i == 0;
         } else {
            return true;
         }
      } else {
         return this == Effects.HUNGER;
      }
   }

   public boolean isInstant() {
      return false;
   }

   protected String getOrCreateDescriptionId() {
      if (this.name == null) {
         this.name = Util.makeTranslationKey("effect", Registry.EFFECTS.getKey(this));
      }

      return this.name;
   }

   public String getName() {
      return this.getOrCreateDescriptionId();
   }

   public ITextComponent getDisplayName() {
      return new TranslationTextComponent(this.getName());
   }

   @OnlyIn(Dist.CLIENT)
   public EffectType getEffectType() {
      return this.type;
   }

   public int getLiquidColor() {
      return this.liquidColor;
   }

   public Effect addAttributesModifier(Attribute attributeIn, String uuid, double amount, AttributeModifier.Operation operation) {
      AttributeModifier attributemodifier = new AttributeModifier(UUID.fromString(uuid), this::getName, amount, operation);
      this.attributeModifierMap.put(attributeIn, attributemodifier);
      return this;
   }

   @OnlyIn(Dist.CLIENT)
   public Map<Attribute, AttributeModifier> getAttributeModifierMap() {
      return this.attributeModifierMap;
   }

   public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
      for(Entry<Attribute, AttributeModifier> entry : this.attributeModifierMap.entrySet()) {
         ModifiableAttributeInstance modifiableattributeinstance = attributeMapIn.func_233779_a_(entry.getKey());
         if (modifiableattributeinstance != null) {
            modifiableattributeinstance.removeModifier(entry.getValue());
         }
      }

   }

   public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
      for(Entry<Attribute, AttributeModifier> entry : this.attributeModifierMap.entrySet()) {
         ModifiableAttributeInstance modifiableattributeinstance = attributeMapIn.func_233779_a_(entry.getKey());
         if (modifiableattributeinstance != null) {
            AttributeModifier attributemodifier = entry.getValue();
            modifiableattributeinstance.removeModifier(attributemodifier);
            modifiableattributeinstance.func_233769_c_(new AttributeModifier(attributemodifier.getID(), this.getName() + " " + amplifier, this.getAttributeModifierAmount(amplifier, attributemodifier), attributemodifier.getOperation()));
         }
      }

   }

   public double getAttributeModifierAmount(int amplifier, AttributeModifier modifier) {
      return modifier.getAmount() * (double)(amplifier + 1);
   }

   @OnlyIn(Dist.CLIENT)
   public boolean isBeneficial() {
      return this.type == EffectType.BENEFICIAL;
   }
}