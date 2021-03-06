package net.minecraft.block;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class FireBlock extends AbstractFireBlock {
   public static final IntegerProperty AGE = BlockStateProperties.AGE_0_15;
   public static final BooleanProperty NORTH = SixWayBlock.NORTH;
   public static final BooleanProperty EAST = SixWayBlock.EAST;
   public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
   public static final BooleanProperty WEST = SixWayBlock.WEST;
   public static final BooleanProperty UP = SixWayBlock.UP;
   private static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = SixWayBlock.FACING_TO_PROPERTY_MAP.entrySet().stream().filter((p_199776_0_) -> {
      return p_199776_0_.getKey() != Direction.DOWN;
   }).collect(Util.toMapCollector());
   private final Object2IntMap<Block> encouragements = new Object2IntOpenHashMap<>();
   private final Object2IntMap<Block> flammabilities = new Object2IntOpenHashMap<>();

   public FireBlock(AbstractBlock.Properties builder) {
      super(builder, 1.0F);
      this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)).with(NORTH, Boolean.valueOf(false)).with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false)).with(WEST, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false)));
   }

   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
      return this.isValidPosition(stateIn, worldIn, currentPos) ? this.func_235494_a_(worldIn, currentPos, stateIn.get(AGE)) : Blocks.AIR.getDefaultState();
   }

   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
      VoxelShape voxelshape = VoxelShapes.empty();
      if (state.get(UP)) {
         voxelshape = field_235319_a_;
      }

      if (state.get(WEST)) {
         voxelshape = VoxelShapes.or(voxelshape, field_235321_c_);
      }

      if (state.get(EAST)) {
         voxelshape = VoxelShapes.or(voxelshape, field_235322_d_);
      }

      if (state.get(NORTH)) {
         voxelshape = VoxelShapes.or(voxelshape, field_235323_e_);
      }

      if (state.get(SOUTH)) {
         voxelshape = VoxelShapes.or(voxelshape, field_235324_f_);
      }

      return voxelshape == VoxelShapes.empty() ? field_235320_b_ : voxelshape;
   }

   public BlockState getStateForPlacement(BlockItemUseContext context) {
      return this.getStateForPlacement(context.getWorld(), context.getPos());
   }

   protected BlockState getStateForPlacement(IBlockReader p_196448_1_, BlockPos p_196448_2_) {
      BlockPos blockpos = p_196448_2_.down();
      BlockState blockstate = p_196448_1_.getBlockState(blockpos);
      if (!this.canBurn(blockstate) && !blockstate.isSolidSide(p_196448_1_, blockpos, Direction.UP)) {
         BlockState blockstate1 = this.getDefaultState();

         for(Direction direction : Direction.values()) {
            BooleanProperty booleanproperty = FACING_TO_PROPERTY_MAP.get(direction);
            if (booleanproperty != null) {
               blockstate1 = blockstate1.with(booleanproperty, Boolean.valueOf(this.canBurn(p_196448_1_.getBlockState(p_196448_2_.offset(direction)))));
            }
         }

         return blockstate1;
      } else {
         return this.getDefaultState();
      }
   }

   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
      BlockPos blockpos = pos.down();
      return worldIn.getBlockState(blockpos).isSolidSide(worldIn, blockpos, Direction.UP) || this.areNeighborsFlammable(worldIn, pos);
   }

   public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
      worldIn.getPendingBlockTicks().scheduleTick(pos, this, func_235495_a_(worldIn.rand));
      if (worldIn.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
         if (!state.isValidPosition(worldIn, pos)) {
            worldIn.removeBlock(pos, false);
         }

         BlockState blockstate = worldIn.getBlockState(pos.down());
         boolean flag = blockstate.func_235714_a_(worldIn.func_230315_m_().func_241515_q_());
         int i = state.get(AGE);
         if (!flag && worldIn.isRaining() && this.canDie(worldIn, pos) && rand.nextFloat() < 0.2F + (float)i * 0.03F) {
            worldIn.removeBlock(pos, false);
         } else {
            int j = Math.min(15, i + rand.nextInt(3) / 2);
            if (i != j) {
               state = state.with(AGE, Integer.valueOf(j));
               worldIn.setBlockState(pos, state, 4);
            }

            if (!flag) {
               if (!this.areNeighborsFlammable(worldIn, pos)) {
                  BlockPos blockpos = pos.down();
                  if (!worldIn.getBlockState(blockpos).isSolidSide(worldIn, blockpos, Direction.UP) || i > 3) {
                     worldIn.removeBlock(pos, false);
                  }

                  return;
               }

               if (i == 15 && rand.nextInt(4) == 0 && !this.canBurn(worldIn.getBlockState(pos.down()))) {
                  worldIn.removeBlock(pos, false);
                  return;
               }
            }

            boolean flag1 = worldIn.isBlockinHighHumidity(pos);
            int k = flag1 ? -50 : 0;
            this.catchOnFire(worldIn, pos.east(), 300 + k, rand, i);
            this.catchOnFire(worldIn, pos.west(), 300 + k, rand, i);
            this.catchOnFire(worldIn, pos.down(), 250 + k, rand, i);
            this.catchOnFire(worldIn, pos.up(), 250 + k, rand, i);
            this.catchOnFire(worldIn, pos.north(), 300 + k, rand, i);
            this.catchOnFire(worldIn, pos.south(), 300 + k, rand, i);
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for(int l = -1; l <= 1; ++l) {
               for(int i1 = -1; i1 <= 1; ++i1) {
                  for(int j1 = -1; j1 <= 4; ++j1) {
                     if (l != 0 || j1 != 0 || i1 != 0) {
                        int k1 = 100;
                        if (j1 > 1) {
                           k1 += (j1 - 1) * 100;
                        }

                        blockpos$mutable.func_239621_a_(pos, l, j1, i1);
                        int l1 = this.getNeighborEncouragement(worldIn, blockpos$mutable);
                        if (l1 > 0) {
                           int i2 = (l1 + 40 + worldIn.getDifficulty().getId() * 7) / (i + 30);
                           if (flag1) {
                              i2 /= 2;
                           }

                           if (i2 > 0 && rand.nextInt(k1) <= i2 && (!worldIn.isRaining() || !this.canDie(worldIn, blockpos$mutable))) {
                              int j2 = Math.min(15, i + rand.nextInt(5) / 4);
                              worldIn.setBlockState(blockpos$mutable, this.func_235494_a_(worldIn, blockpos$mutable, j2), 3);
                           }
                        }
                     }
                  }
               }
            }

         }
      }
   }

   protected boolean canDie(World worldIn, BlockPos pos) {
      return worldIn.isRainingAt(pos) || worldIn.isRainingAt(pos.west()) || worldIn.isRainingAt(pos.east()) || worldIn.isRainingAt(pos.north()) || worldIn.isRainingAt(pos.south());
   }

   private int func_220274_q(BlockState p_220274_1_) {
      return p_220274_1_.func_235901_b_(BlockStateProperties.WATERLOGGED) && p_220274_1_.get(BlockStateProperties.WATERLOGGED) ? 0 : this.flammabilities.getInt(p_220274_1_.getBlock());
   }

   private int func_220275_r(BlockState p_220275_1_) {
      return p_220275_1_.func_235901_b_(BlockStateProperties.WATERLOGGED) && p_220275_1_.get(BlockStateProperties.WATERLOGGED) ? 0 : this.encouragements.getInt(p_220275_1_.getBlock());
   }

   private void catchOnFire(World worldIn, BlockPos pos, int chance, Random random, int age) {
      int i = this.func_220274_q(worldIn.getBlockState(pos));
      if (random.nextInt(chance) < i) {
         BlockState blockstate = worldIn.getBlockState(pos);
         if (random.nextInt(age + 10) < 5 && !worldIn.isRainingAt(pos)) {
            int j = Math.min(age + random.nextInt(5) / 4, 15);
            worldIn.setBlockState(pos, this.func_235494_a_(worldIn, pos, j), 3);
         } else {
            worldIn.removeBlock(pos, false);
         }

         Block block = blockstate.getBlock();
         if (block instanceof TNTBlock) {
            TNTBlock tntblock = (TNTBlock)block;
            TNTBlock.explode(worldIn, pos);
         }
      }

   }

   private BlockState func_235494_a_(IWorld p_235494_1_, BlockPos p_235494_2_, int p_235494_3_) {
      BlockState blockstate = func_235326_a_(p_235494_1_, p_235494_2_);
      return blockstate.isIn(Blocks.FIRE) ? blockstate.with(AGE, Integer.valueOf(p_235494_3_)) : blockstate;
   }

   private boolean areNeighborsFlammable(IBlockReader worldIn, BlockPos pos) {
      for(Direction direction : Direction.values()) {
         if (this.canBurn(worldIn.getBlockState(pos.offset(direction)))) {
            return true;
         }
      }

      return false;
   }

   private int getNeighborEncouragement(IWorldReader worldIn, BlockPos pos) {
      if (!worldIn.isAirBlock(pos)) {
         return 0;
      } else {
         int i = 0;

         for(Direction direction : Direction.values()) {
            BlockState blockstate = worldIn.getBlockState(pos.offset(direction));
            i = Math.max(this.func_220275_r(blockstate), i);
         }

         return i;
      }
   }

   protected boolean canBurn(BlockState p_196446_1_) {
      return this.func_220275_r(p_196446_1_) > 0;
   }

   public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
      super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
      worldIn.getPendingBlockTicks().scheduleTick(pos, this, func_235495_a_(worldIn.rand));
   }

   private static int func_235495_a_(Random p_235495_0_) {
      return 30 + p_235495_0_.nextInt(10);
   }

   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
      builder.add(AGE, NORTH, EAST, SOUTH, WEST, UP);
   }

   private void setFireInfo(Block blockIn, int encouragement, int flammability) {
      this.encouragements.put(blockIn, encouragement);
      this.flammabilities.put(blockIn, flammability);
   }

   public static void init() {
      FireBlock fireblock = (FireBlock)Blocks.FIRE;
      fireblock.setFireInfo(Blocks.OAK_PLANKS, 5, 20);
      fireblock.setFireInfo(Blocks.SPRUCE_PLANKS, 5, 20);
      fireblock.setFireInfo(Blocks.BIRCH_PLANKS, 5, 20);
      fireblock.setFireInfo(Blocks.JUNGLE_PLANKS, 5, 20);
      fireblock.setFireInfo(Blocks.ACACIA_PLANKS, 5, 20);
      fireblock.setFireInfo(Blocks.DARK_OAK_PLANKS, 5, 20);
      fireblock.setFireInfo(Blocks.OAK_SLAB, 5, 20);
      fireblock.setFireInfo(Blocks.SPRUCE_SLAB, 5, 20);
      fireblock.setFireInfo(Blocks.BIRCH_SLAB, 5, 20);
      fireblock.setFireInfo(Blocks.JUNGLE_SLAB, 5, 20);
      fireblock.setFireInfo(Blocks.ACACIA_SLAB, 5, 20);
      fireblock.setFireInfo(Blocks.DARK_OAK_SLAB, 5, 20);
      fireblock.setFireInfo(Blocks.OAK_FENCE_GATE, 5, 20);
      fireblock.setFireInfo(Blocks.SPRUCE_FENCE_GATE, 5, 20);
      fireblock.setFireInfo(Blocks.BIRCH_FENCE_GATE, 5, 20);
      fireblock.setFireInfo(Blocks.JUNGLE_FENCE_GATE, 5, 20);
      fireblock.setFireInfo(Blocks.DARK_OAK_FENCE_GATE, 5, 20);
      fireblock.setFireInfo(Blocks.ACACIA_FENCE_GATE, 5, 20);
      fireblock.setFireInfo(Blocks.OAK_FENCE, 5, 20);
      fireblock.setFireInfo(Blocks.SPRUCE_FENCE, 5, 20);
      fireblock.setFireInfo(Blocks.BIRCH_FENCE, 5, 20);
      fireblock.setFireInfo(Blocks.JUNGLE_FENCE, 5, 20);
      fireblock.setFireInfo(Blocks.DARK_OAK_FENCE, 5, 20);
      fireblock.setFireInfo(Blocks.ACACIA_FENCE, 5, 20);
      fireblock.setFireInfo(Blocks.OAK_STAIRS, 5, 20);
      fireblock.setFireInfo(Blocks.BIRCH_STAIRS, 5, 20);
      fireblock.setFireInfo(Blocks.SPRUCE_STAIRS, 5, 20);
      fireblock.setFireInfo(Blocks.JUNGLE_STAIRS, 5, 20);
      fireblock.setFireInfo(Blocks.ACACIA_STAIRS, 5, 20);
      fireblock.setFireInfo(Blocks.DARK_OAK_STAIRS, 5, 20);
      fireblock.setFireInfo(Blocks.OAK_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.SPRUCE_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.BIRCH_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.JUNGLE_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.ACACIA_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.DARK_OAK_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_OAK_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_SPRUCE_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_BIRCH_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_JUNGLE_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_ACACIA_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_DARK_OAK_LOG, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_OAK_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_SPRUCE_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_BIRCH_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_JUNGLE_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_ACACIA_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.STRIPPED_DARK_OAK_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.OAK_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.SPRUCE_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.BIRCH_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.JUNGLE_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.ACACIA_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.DARK_OAK_WOOD, 5, 5);
      fireblock.setFireInfo(Blocks.OAK_LEAVES, 30, 60);
      fireblock.setFireInfo(Blocks.SPRUCE_LEAVES, 30, 60);
      fireblock.setFireInfo(Blocks.BIRCH_LEAVES, 30, 60);
      fireblock.setFireInfo(Blocks.JUNGLE_LEAVES, 30, 60);
      fireblock.setFireInfo(Blocks.ACACIA_LEAVES, 30, 60);
      fireblock.setFireInfo(Blocks.DARK_OAK_LEAVES, 30, 60);
      fireblock.setFireInfo(Blocks.BOOKSHELF, 30, 20);
      fireblock.setFireInfo(Blocks.TNT, 15, 100);
      fireblock.setFireInfo(Blocks.GRASS, 60, 100);
      fireblock.setFireInfo(Blocks.FERN, 60, 100);
      fireblock.setFireInfo(Blocks.DEAD_BUSH, 60, 100);
      fireblock.setFireInfo(Blocks.SUNFLOWER, 60, 100);
      fireblock.setFireInfo(Blocks.LILAC, 60, 100);
      fireblock.setFireInfo(Blocks.ROSE_BUSH, 60, 100);
      fireblock.setFireInfo(Blocks.PEONY, 60, 100);
      fireblock.setFireInfo(Blocks.TALL_GRASS, 60, 100);
      fireblock.setFireInfo(Blocks.LARGE_FERN, 60, 100);
      fireblock.setFireInfo(Blocks.DANDELION, 60, 100);
      fireblock.setFireInfo(Blocks.POPPY, 60, 100);
      fireblock.setFireInfo(Blocks.BLUE_ORCHID, 60, 100);
      fireblock.setFireInfo(Blocks.ALLIUM, 60, 100);
      fireblock.setFireInfo(Blocks.AZURE_BLUET, 60, 100);
      fireblock.setFireInfo(Blocks.RED_TULIP, 60, 100);
      fireblock.setFireInfo(Blocks.ORANGE_TULIP, 60, 100);
      fireblock.setFireInfo(Blocks.WHITE_TULIP, 60, 100);
      fireblock.setFireInfo(Blocks.PINK_TULIP, 60, 100);
      fireblock.setFireInfo(Blocks.OXEYE_DAISY, 60, 100);
      fireblock.setFireInfo(Blocks.CORNFLOWER, 60, 100);
      fireblock.setFireInfo(Blocks.LILY_OF_THE_VALLEY, 60, 100);
      fireblock.setFireInfo(Blocks.WITHER_ROSE, 60, 100);
      fireblock.setFireInfo(Blocks.WHITE_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.ORANGE_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.MAGENTA_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.LIGHT_BLUE_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.YELLOW_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.LIME_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.PINK_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.GRAY_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.LIGHT_GRAY_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.CYAN_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.PURPLE_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.BLUE_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.BROWN_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.GREEN_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.RED_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.BLACK_WOOL, 30, 60);
      fireblock.setFireInfo(Blocks.VINE, 15, 100);
      fireblock.setFireInfo(Blocks.COAL_BLOCK, 5, 5);
      fireblock.setFireInfo(Blocks.HAY_BLOCK, 60, 20);
      fireblock.setFireInfo(Blocks.field_235396_nb_, 15, 20);
      fireblock.setFireInfo(Blocks.WHITE_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.ORANGE_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.MAGENTA_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.LIGHT_BLUE_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.YELLOW_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.LIME_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.PINK_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.GRAY_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.LIGHT_GRAY_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.CYAN_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.PURPLE_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.BLUE_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.BROWN_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.GREEN_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.RED_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.BLACK_CARPET, 60, 20);
      fireblock.setFireInfo(Blocks.DRIED_KELP_BLOCK, 30, 60);
      fireblock.setFireInfo(Blocks.BAMBOO, 60, 60);
      fireblock.setFireInfo(Blocks.SCAFFOLDING, 60, 60);
      fireblock.setFireInfo(Blocks.LECTERN, 30, 20);
      fireblock.setFireInfo(Blocks.COMPOSTER, 5, 20);
      fireblock.setFireInfo(Blocks.SWEET_BERRY_BUSH, 60, 100);
      fireblock.setFireInfo(Blocks.BEEHIVE, 5, 20);
      fireblock.setFireInfo(Blocks.BEE_NEST, 30, 20);
   }
}