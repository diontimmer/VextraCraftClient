package net.minecraft.tileentity;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.function.UnaryOperator;
import javax.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SignTileEntity extends TileEntity {
   private final ITextComponent[] signText = new ITextComponent[]{StringTextComponent.field_240750_d_, StringTextComponent.field_240750_d_, StringTextComponent.field_240750_d_, StringTextComponent.field_240750_d_};
   private boolean isEditable = true;
   private PlayerEntity player;
   private final ITextProperties[] renderText = new ITextProperties[4];
   private DyeColor textColor = DyeColor.BLACK;

   public SignTileEntity() {
      super(TileEntityType.SIGN);
   }

   public CompoundNBT write(CompoundNBT compound) {
      super.write(compound);

      for(int i = 0; i < 4; ++i) {
         String s = ITextComponent.Serializer.toJson(this.signText[i]);
         compound.putString("Text" + (i + 1), s);
      }

      compound.putString("Color", this.textColor.getTranslationKey());
      return compound;
   }

   public void func_230337_a_(BlockState p_230337_1_, CompoundNBT p_230337_2_) {
      this.isEditable = false;
      super.func_230337_a_(p_230337_1_, p_230337_2_);
      this.textColor = DyeColor.byTranslationKey(p_230337_2_.getString("Color"), DyeColor.BLACK);

      for(int i = 0; i < 4; ++i) {
         String s = p_230337_2_.getString("Text" + (i + 1));
         ITextComponent itextcomponent = ITextComponent.Serializer.func_240643_a_(s.isEmpty() ? "\"\"" : s);
         if (this.world instanceof ServerWorld) {
            try {
               this.signText[i] = TextComponentUtils.func_240645_a_(this.getCommandSource((ServerPlayerEntity)null), itextcomponent, (Entity)null, 0);
            } catch (CommandSyntaxException commandsyntaxexception) {
               this.signText[i] = itextcomponent;
            }
         } else {
            this.signText[i] = itextcomponent;
         }

         this.renderText[i] = null;
      }

   }

   public void setText(int line, ITextComponent p_212365_2_) {
      this.signText[line] = p_212365_2_;
      this.renderText[line] = null;
   }

   @Nullable
   @OnlyIn(Dist.CLIENT)
   public ITextProperties func_235677_a_(int p_235677_1_, UnaryOperator<ITextProperties> p_235677_2_) {
      if (this.renderText[p_235677_1_] == null && this.signText[p_235677_1_] != null) {
         this.renderText[p_235677_1_] = p_235677_2_.apply(this.signText[p_235677_1_]);
      }

      return this.renderText[p_235677_1_];
   }

   @Nullable
   public SUpdateTileEntityPacket getUpdatePacket() {
      return new SUpdateTileEntityPacket(this.pos, 9, this.getUpdateTag());
   }

   public CompoundNBT getUpdateTag() {
      return this.write(new CompoundNBT());
   }

   public boolean onlyOpsCanSetNbt() {
      return true;
   }

   public boolean getIsEditable() {
      return this.isEditable;
   }

   @OnlyIn(Dist.CLIENT)
   public void setEditable(boolean isEditableIn) {
      this.isEditable = isEditableIn;
      if (!isEditableIn) {
         this.player = null;
      }

   }

   public void setPlayer(PlayerEntity playerIn) {
      this.player = playerIn;
   }

   public PlayerEntity getPlayer() {
      return this.player;
   }

   public boolean executeCommand(PlayerEntity playerIn) {
      for(ITextComponent itextcomponent : this.signText) {
         Style style = itextcomponent == null ? null : itextcomponent.getStyle();
         if (style != null && style.getClickEvent() != null) {
            ClickEvent clickevent = style.getClickEvent();
            if (clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
               playerIn.getServer().getCommandManager().handleCommand(this.getCommandSource((ServerPlayerEntity)playerIn), clickevent.getValue());
            }
         }
      }

      return true;
   }

   public CommandSource getCommandSource(@Nullable ServerPlayerEntity playerIn) {
      String s = playerIn == null ? "Sign" : playerIn.getName().getString();
      ITextComponent itextcomponent = (ITextComponent)(playerIn == null ? new StringTextComponent("Sign") : playerIn.getDisplayName());
      return new CommandSource(ICommandSource.DUMMY, Vector3d.func_237489_a_(this.pos), Vector2f.ZERO, (ServerWorld)this.world, 2, s, itextcomponent, this.world.getServer(), playerIn);
   }

   public DyeColor getTextColor() {
      return this.textColor;
   }

   public boolean setTextColor(DyeColor newColor) {
      if (newColor != this.getTextColor()) {
         this.textColor = newColor;
         this.markDirty();
         this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
         return true;
      } else {
         return false;
      }
   }
}