package net.minecraft.block.trees;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

public class DarkOakTree extends BigTree {
   @Nullable
   protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
      return null;
   }

   @Nullable
   protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(Random p_225547_1_) {
      return Feature.field_236291_c_.withConfiguration(DefaultBiomeFeatures.DARK_OAK_TREE_CONFIG);
   }
}