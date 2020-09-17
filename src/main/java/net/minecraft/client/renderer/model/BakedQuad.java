package net.minecraft.client.renderer.model;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BakedQuad {
   protected final int[] vertexData;
   protected final int tintIndex;
   protected final Direction face;
   protected final TextureAtlasSprite sprite;
   private final boolean field_239286_e_;

   public BakedQuad(int[] p_i232466_1_, int p_i232466_2_, Direction p_i232466_3_, TextureAtlasSprite p_i232466_4_, boolean p_i232466_5_) {
      this.vertexData = p_i232466_1_;
      this.tintIndex = p_i232466_2_;
      this.face = p_i232466_3_;
      this.sprite = p_i232466_4_;
      this.field_239286_e_ = p_i232466_5_;
   }

   public int[] getVertexData() {
      return this.vertexData;
   }

   public boolean hasTintIndex() {
      return this.tintIndex != -1;
   }

   public int getTintIndex() {
      return this.tintIndex;
   }

   public Direction getFace() {
      return this.face;
   }

   public boolean func_239287_f_() {
      return this.field_239286_e_;
   }
}