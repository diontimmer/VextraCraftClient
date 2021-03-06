package net.minecraft.item;

import java.util.Optional;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CompassItem extends Item implements IVanishable {
   private static final Logger field_234666_a_ = LogManager.getLogger();

   public CompassItem(Item.Properties builder) {
      super(builder);
   }

   public static boolean func_234670_d_(ItemStack p_234670_0_) {
      CompoundNBT compoundnbt = p_234670_0_.getTag();
      return compoundnbt != null && (compoundnbt.contains("LodestoneDimension") || compoundnbt.contains("LodestonePos"));
   }

   public boolean hasEffect(ItemStack stack) {
      return func_234670_d_(stack) || super.hasEffect(stack);
   }

   public static Optional<RegistryKey<World>> func_234667_a_(CompoundNBT p_234667_0_) {
      return World.field_234917_f_.parse(NBTDynamicOps.INSTANCE, p_234667_0_.get("LodestoneDimension")).result();
   }

   public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
      if (!worldIn.isRemote) {
         if (func_234670_d_(stack)) {
            CompoundNBT compoundnbt = stack.getOrCreateTag();
            if (compoundnbt.contains("LodestoneTracked") && !compoundnbt.getBoolean("LodestoneTracked")) {
               return;
            }

            Optional<RegistryKey<World>> optional = func_234667_a_(compoundnbt);
            if (optional.isPresent() && optional.get() == worldIn.func_234923_W_() && compoundnbt.contains("LodestonePos") && !((ServerWorld)worldIn).getPointOfInterestManager().func_234135_a_(PointOfInterestType.field_234166_w_, NBTUtil.readBlockPos(compoundnbt.getCompound("LodestonePos")))) {
               compoundnbt.remove("LodestonePos");
            }
         }

      }
   }

   public ActionResultType onItemUse(ItemUseContext context) {
      BlockPos blockpos = context.rayTraceResult.getPos();
      if (!context.world.getBlockState(blockpos).isIn(Blocks.field_235405_no_)) {
         return super.onItemUse(context);
      } else {
         context.world.playSound((PlayerEntity)null, blockpos, SoundEvents.field_232731_hu_, SoundCategory.PLAYERS, 1.0F, 1.0F);
         boolean flag = !context.player.abilities.isCreativeMode && context.item.getCount() == 1;
         if (flag) {
            this.func_234669_a_(context.world.func_234923_W_(), blockpos, context.item.getOrCreateTag());
         } else {
            ItemStack itemstack = new ItemStack(Items.COMPASS, 1);
            CompoundNBT compoundnbt = context.item.hasTag() ? context.item.getTag().copy() : new CompoundNBT();
            itemstack.setTag(compoundnbt);
            if (!context.player.abilities.isCreativeMode) {
               context.item.shrink(1);
            }

            this.func_234669_a_(context.world.func_234923_W_(), blockpos, compoundnbt);
            if (!context.player.inventory.addItemStackToInventory(itemstack)) {
               context.player.dropItem(itemstack, false);
            }
         }

         return ActionResultType.func_233537_a_(context.world.isRemote);
      }
   }

   private void func_234669_a_(RegistryKey<World> p_234669_1_, BlockPos p_234669_2_, CompoundNBT p_234669_3_) {
      p_234669_3_.put("LodestonePos", NBTUtil.writeBlockPos(p_234669_2_));
      World.field_234917_f_.encodeStart(NBTDynamicOps.INSTANCE, p_234669_1_).resultOrPartial(field_234666_a_::error).ifPresent((p_234668_1_) -> {
         p_234669_3_.put("LodestoneDimension", p_234668_1_);
      });
      p_234669_3_.putBoolean("LodestoneTracked", true);
   }

   public String getTranslationKey(ItemStack stack) {
      return func_234670_d_(stack) ? "item.minecraft.lodestone_compass" : super.getTranslationKey(stack);
   }
}