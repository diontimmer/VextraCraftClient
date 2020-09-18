package net.minecraft.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.List;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.ServerPinger;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@OnlyIn(Dist.CLIENT)
public class MultiplayerScreen extends Screen {
   private static final Logger LOGGER = LogManager.getLogger();
   private final ServerPinger oldServerPinger = new ServerPinger();
   private final Screen parentScreen;
   protected ServerSelectionList serverListSelector;
   private ServerList savedServerList;
   private List<ITextComponent> hoveringText;
   private boolean initialized;

   public MultiplayerScreen(Screen parentScreen) {
      super(new TranslationTextComponent("multiplayer.title"));
      this.parentScreen = parentScreen;
   }

   protected void func_231160_c_() {
      super.func_231160_c_();
      this.field_230706_i_.keyboardListener.enableRepeatEvents(true);
      if (this.initialized) {
         this.serverListSelector.func_230940_a_(this.field_230708_k_, this.field_230709_l_, 32, this.field_230709_l_ - 64);
      } else {
         this.initialized = true;
         this.savedServerList = new ServerList(this.field_230706_i_);
         this.savedServerList.loadServerList();

         this.serverListSelector = new ServerSelectionList(this, this.field_230706_i_, this.field_230708_k_, this.field_230709_l_, 32, this.field_230709_l_ - 64, 36);
         this.serverListSelector.updateOnlineServers(this.savedServerList);
      }

      this.field_230705_e_.add(this.serverListSelector);
      this.func_230480_a_(new Button(this.field_230708_k_ / 2 + 4, this.field_230709_l_ - 28, 70, 20, new TranslationTextComponent("selectServer.refresh"), (p_214291_1_) -> {
         this.refreshServerList();
      }));
      this.func_230480_a_(new Button(this.field_230708_k_ / 2 + 4 + 76, this.field_230709_l_ - 28, 75, 20, DialogTexts.field_240633_d_, (p_214289_1_) -> {
         this.field_230706_i_.displayGuiScreen(this.parentScreen);
      }));
      this.func_214295_b();
   }

   public void func_231023_e_() {
      super.func_231023_e_();

      this.oldServerPinger.pingPendingNetworks();
   }

   public void func_231164_f_() {
      this.field_230706_i_.keyboardListener.enableRepeatEvents(false);

      this.oldServerPinger.clearPendingNetworks();
   }

   private void refreshServerList() {
      this.field_230706_i_.displayGuiScreen(new MultiplayerScreen(this.parentScreen));
   }

   private void func_214285_a(boolean p_214285_1_) {
      ServerSelectionList.Entry serverselectionlist$entry = this.serverListSelector.func_230958_g_();
      if (p_214285_1_ && serverselectionlist$entry instanceof ServerSelectionList.NormalEntry) {
         this.savedServerList.func_217506_a(((ServerSelectionList.NormalEntry)serverselectionlist$entry).getServerData());
         this.savedServerList.saveServerList();
         this.serverListSelector.func_241215_a_((ServerSelectionList.Entry)null);
         this.serverListSelector.updateOnlineServers(this.savedServerList);
      }

      this.field_230706_i_.displayGuiScreen(this);
   }



   public boolean func_231046_a_(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
      if (super.func_231046_a_(p_231046_1_, p_231046_2_, p_231046_3_)) {
         return true;
      } else if (p_231046_1_ == 294) {
         this.refreshServerList();
         return true;
      } else if (this.serverListSelector.func_230958_g_() != null) {
         if (p_231046_1_ != 257 && p_231046_1_ != 335) {
            return this.serverListSelector.func_231046_a_(p_231046_1_, p_231046_2_, p_231046_3_);
         } else {
            this.connectToSelected();
            return true;
         }
      } else {
         return false;
      }
   }

   public void func_230430_a_(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
      this.hoveringText = null;
      this.func_230446_a_(p_230430_1_);
      this.serverListSelector.func_230430_a_(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
      this.func_238472_a_(p_230430_1_, this.field_230712_o_, this.field_230704_d_, this.field_230708_k_ / 2, 20, 16777215);
      super.func_230430_a_(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
      if (this.hoveringText != null) {
         this.func_238654_b_(p_230430_1_, this.hoveringText, p_230430_2_, p_230430_3_);
      }

   }

   public void connectToSelected() {
      ServerSelectionList.Entry serverselectionlist$entry = this.serverListSelector.func_230958_g_();
      if (serverselectionlist$entry instanceof ServerSelectionList.NormalEntry) {
         this.connectToServer(((ServerSelectionList.NormalEntry)serverselectionlist$entry).getServerData());
      }

   }

   private void connectToServer(ServerData server) {
      this.field_230706_i_.displayGuiScreen(new ConnectingScreen(this, this.field_230706_i_, server));
   }

   public void func_214287_a(ServerSelectionList.Entry p_214287_1_) {
      this.serverListSelector.func_241215_a_(p_214287_1_);
      this.func_214295_b();
   }

   protected void func_214295_b() {
      ServerSelectionList.Entry serverselectionlist$entry = this.serverListSelector.func_230958_g_();

   }

   public ServerPinger getOldServerPinger() {
      return this.oldServerPinger;
   }

   public void func_238854_b_(List<ITextComponent> p_238854_1_) {
      this.hoveringText = p_238854_1_;
   }

   public ServerList getServerList() {
      return this.savedServerList;
   }
}