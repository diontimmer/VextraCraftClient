package net.minecraft.block;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class NoteBlock extends Block {
   public static final EnumProperty<NoteBlockInstrument> INSTRUMENT = BlockStateProperties.NOTE_BLOCK_INSTRUMENT;
   public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
   public static final IntegerProperty NOTE = BlockStateProperties.NOTE_0_24;

   public NoteBlock(AbstractBlock.Properties properties) {
      super(properties);
      this.setDefaultState(this.stateContainer.getBaseState().with(INSTRUMENT, NoteBlockInstrument.HARP).with(NOTE, Integer.valueOf(0)).with(POWERED, Boolean.valueOf(false)));
   }

   public BlockState getStateForPlacement(BlockItemUseContext context) {
      return this.getDefaultState().with(INSTRUMENT, NoteBlockInstrument.byState(context.getWorld().getBlockState(context.getPos().down())));
   }

   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
      return facing == Direction.DOWN ? stateIn.with(INSTRUMENT, NoteBlockInstrument.byState(facingState)) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
   }

   public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
      boolean flag = worldIn.isBlockPowered(pos);
      if (flag != state.get(POWERED)) {
         if (flag) {
            this.triggerNote(worldIn, pos);
         }

         worldIn.setBlockState(pos, state.with(POWERED, Boolean.valueOf(flag)), 3);
      }

   }

   private void triggerNote(World worldIn, BlockPos pos) {
      if (worldIn.getBlockState(pos.up()).isAir()) {
         worldIn.addBlockEvent(pos, this, 0, 0);
      }

   }

   public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
      if (worldIn.isRemote) {
         return ActionResultType.SUCCESS;
      } else {
         state = state.func_235896_a_(NOTE);
         worldIn.setBlockState(pos, state, 3);
         this.triggerNote(worldIn, pos);
         player.addStat(Stats.TUNE_NOTEBLOCK);
         return ActionResultType.CONSUME;
      }
   }

   public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
      if (!worldIn.isRemote) {
         this.triggerNote(worldIn, pos);
         player.addStat(Stats.PLAY_NOTEBLOCK);
      }
   }

   public boolean eventReceived(BlockState state, World worldIn, BlockPos pos, int id, int param) {
      int i = state.get(NOTE);
      float f = (float)Math.pow(2.0D, (double)(i - 12) / 12.0D);
      worldIn.playSound((PlayerEntity)null, pos, state.get(INSTRUMENT).getSound(), SoundCategory.RECORDS, 3.0F, f);
      worldIn.addParticle(ParticleTypes.NOTE, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.2D, (double)pos.getZ() + 0.5D, (double)i / 24.0D, 0.0D, 0.0D);
      return true;
   }

   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
      builder.add(INSTRUMENT, POWERED, NOTE);
   }
}