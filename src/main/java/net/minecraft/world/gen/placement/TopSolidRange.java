package net.minecraft.world.gen.placement;

import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;

public class TopSolidRange extends Placement<TopSolidRangeConfig> {
   public TopSolidRange(Codec<TopSolidRangeConfig> p_i232098_1_) {
      super(p_i232098_1_);
   }

   public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator generatorIn, Random random, TopSolidRangeConfig configIn, BlockPos pos) {
      int i = random.nextInt(configIn.max - configIn.min) + configIn.min;
      return IntStream.range(0, i).mapToObj((p_227452_3_) -> {
         int j = random.nextInt(16) + pos.getX();
         int k = random.nextInt(16) + pos.getZ();
         int l = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, j, k);
         return new BlockPos(j, l, k);
      });
   }
}