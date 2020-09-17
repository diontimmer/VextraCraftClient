package net.minecraft.item;

import java.util.Random;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Rotations;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ArmorStandItem extends Item {
   public ArmorStandItem(Item.Properties builder) {
      super(builder);
   }

   public ActionResultType onItemUse(ItemUseContext context) {
      Direction direction = context.getFace();
      if (direction == Direction.DOWN) {
         return ActionResultType.FAIL;
      } else {
         World world = context.getWorld();
         BlockItemUseContext blockitemusecontext = new BlockItemUseContext(context);
         BlockPos blockpos = blockitemusecontext.getPos();
         ItemStack itemstack = context.getItem();
         ArmorStandEntity armorstandentity = EntityType.ARMOR_STAND.create(world, itemstack.getTag(), (ITextComponent)null, context.getPlayer(), blockpos, SpawnReason.SPAWN_EGG, true, true);
         if (world.hasNoCollisions(armorstandentity) && world.getEntitiesWithinAABBExcludingEntity(armorstandentity, armorstandentity.getBoundingBox()).isEmpty()) {
            if (!world.isRemote) {
               float f = (float)MathHelper.floor((MathHelper.wrapDegrees(context.getPlacementYaw() - 180.0F) + 22.5F) / 45.0F) * 45.0F;
               armorstandentity.setLocationAndAngles(armorstandentity.getPosX(), armorstandentity.getPosY(), armorstandentity.getPosZ(), f, 0.0F);
               this.applyRandomRotations(armorstandentity, world.rand);
               world.addEntity(armorstandentity);
               world.playSound((PlayerEntity)null, armorstandentity.getPosX(), armorstandentity.getPosY(), armorstandentity.getPosZ(), SoundEvents.ENTITY_ARMOR_STAND_PLACE, SoundCategory.BLOCKS, 0.75F, 0.8F);
            }

            itemstack.shrink(1);
            return ActionResultType.func_233537_a_(world.isRemote);
         } else {
            return ActionResultType.FAIL;
         }
      }
   }

   private void applyRandomRotations(ArmorStandEntity armorStand, Random rand) {
      Rotations rotations = armorStand.getHeadRotation();
      float f = rand.nextFloat() * 5.0F;
      float f1 = rand.nextFloat() * 20.0F - 10.0F;
      Rotations rotations1 = new Rotations(rotations.getX() + f, rotations.getY() + f1, rotations.getZ());
      armorStand.setHeadRotation(rotations1);
      rotations = armorStand.getBodyRotation();
      f = rand.nextFloat() * 10.0F - 5.0F;
      rotations1 = new Rotations(rotations.getX(), rotations.getY() + f, rotations.getZ());
      armorStand.setBodyRotation(rotations1);
   }
}