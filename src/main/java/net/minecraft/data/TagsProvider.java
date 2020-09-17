package net.minecraft.data;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.tags.ITag;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class TagsProvider<T> implements IDataProvider {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
   protected final DataGenerator generator;
   protected final Registry<T> registry;
   private final Map<ResourceLocation, ITag.Builder> tagToBuilder = Maps.newLinkedHashMap();

   protected TagsProvider(DataGenerator generatorIn, Registry<T> registryIn) {
      this.generator = generatorIn;
      this.registry = registryIn;
   }

   protected abstract void registerTags();

   public void act(DirectoryCache cache) {
      this.tagToBuilder.clear();
      this.registerTags();
      ITag<T> itag = Tag.func_241284_a_();
      Function<ResourceLocation, ITag<T>> function = (p_240523_2_) -> {
         return this.tagToBuilder.containsKey(p_240523_2_) ? itag : null;
      };
      Function<ResourceLocation, T> function1 = (p_240527_1_) -> {
         return this.registry.getValue(p_240527_1_).orElse((T)null);
      };
      this.tagToBuilder.forEach((p_240524_4_, p_240524_5_) -> {
         List<ITag.Proxy> list = p_240524_5_.func_232963_b_(function, function1).collect(Collectors.toList());
         if (!list.isEmpty()) {
            throw new IllegalArgumentException(String.format("Couldn't define tag %s as it is missing following references: %s", p_240524_4_, list.stream().map(Objects::toString).collect(Collectors.joining(","))));
         } else {
            JsonObject jsonobject = p_240524_5_.func_232965_c_();
            Path path = this.makePath(p_240524_4_);

            try {
               String s = GSON.toJson((JsonElement)jsonobject);
               String s1 = HASH_FUNCTION.hashUnencodedChars(s).toString();
               if (!Objects.equals(cache.getPreviousHash(path), s1) || !Files.exists(path)) {
                  Files.createDirectories(path.getParent());

                  try (BufferedWriter bufferedwriter = Files.newBufferedWriter(path)) {
                     bufferedwriter.write(s);
                  }
               }

               cache.recordHash(path, s1);
            } catch (IOException ioexception) {
               LOGGER.error("Couldn't save tags to {}", path, ioexception);
            }

         }
      });
   }

   protected abstract Path makePath(ResourceLocation id);

   protected TagsProvider.Builder<T> func_240522_a_(ITag.INamedTag<T> p_240522_1_) {
      ITag.Builder itag$builder = this.func_240525_b_(p_240522_1_);
      return new TagsProvider.Builder<>(itag$builder, this.registry, "vanilla");
   }

   protected ITag.Builder func_240525_b_(ITag.INamedTag<T> p_240525_1_) {
      return this.tagToBuilder.computeIfAbsent(p_240525_1_.func_230234_a_(), (p_240526_0_) -> {
         return new ITag.Builder();
      });
   }

   public static class Builder<T> {
      private final ITag.Builder field_240528_a_;
      private final Registry<T> field_240529_b_;
      private final String field_240530_c_;

      private Builder(ITag.Builder p_i232553_1_, Registry<T> p_i232553_2_, String p_i232553_3_) {
         this.field_240528_a_ = p_i232553_1_;
         this.field_240529_b_ = p_i232553_2_;
         this.field_240530_c_ = p_i232553_3_;
      }

      public TagsProvider.Builder<T> func_240532_a_(T p_240532_1_) {
         this.field_240528_a_.func_232961_a_(this.field_240529_b_.getKey(p_240532_1_), this.field_240530_c_);
         return this;
      }

      public TagsProvider.Builder<T> func_240531_a_(ITag.INamedTag<T> p_240531_1_) {
         this.field_240528_a_.func_232964_b_(p_240531_1_.func_230234_a_(), this.field_240530_c_);
         return this;
      }

      @SafeVarargs
      public final TagsProvider.Builder<T> func_240534_a_(T... p_240534_1_) {
         Stream.<T>of(p_240534_1_).map(this.field_240529_b_::getKey).forEach((p_240533_1_) -> {
            this.field_240528_a_.func_232961_a_(p_240533_1_, this.field_240530_c_);
         });
         return this;
      }
   }
}