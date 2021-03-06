package net.minecraft.world.gen.placement;

import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import net.minecraft.util.math.BlockPos;

public class CountRange extends SimplePlacement<CountRangeConfig> {
   public CountRange(Codec<CountRangeConfig> p_i232100_1_) {
      super(p_i232100_1_);
   }

   public Stream<BlockPos> getPositions(Random random, CountRangeConfig p_212852_2_, BlockPos pos) {
      return IntStream.range(0, p_212852_2_.count).mapToObj((p_227453_3_) -> {
         int i = random.nextInt(16) + pos.getX();
         int j = random.nextInt(16) + pos.getZ();
         int k = random.nextInt(p_212852_2_.maximum - p_212852_2_.topOffset) + p_212852_2_.bottomOffset;
         return new BlockPos(i, k, j);
      });
   }
}