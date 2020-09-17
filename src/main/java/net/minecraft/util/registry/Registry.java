package net.minecraft.util.registry;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Keyable;
import com.mojang.serialization.Lifecycle;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.schedule.Schedule;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.item.PaintingType;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootEntryManager;
import net.minecraft.loot.LootFunctionType;
import net.minecraft.loot.LootPoolEntryType;
import net.minecraft.loot.conditions.LootConditionManager;
import net.minecraft.loot.functions.LootFunctionManager;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IObjectIntIterable;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSizeType;
import net.minecraft.world.gen.feature.jigsaw.IJigsawDeserializer;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.IPosRuleTests;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Registry<T> implements Codec<T>, Keyable, IObjectIntIterable<T> {
   protected static final Logger LOGGER = LogManager.getLogger();
   private static final Map<ResourceLocation, Supplier<?>> LOCATION_TO_SUPPLIER = Maps.newLinkedHashMap();
   public static final ResourceLocation field_239706_f_ = new ResourceLocation("root");
   protected static final MutableRegistry<MutableRegistry<?>> field_239707_g_ = new SimpleRegistry<>(func_239741_a_("root"), Lifecycle.experimental());
   public static final Registry<? extends Registry<?>> REGISTRY = field_239707_g_;
   public static final RegistryKey<Registry<SoundEvent>> field_239708_i_ = func_239741_a_("sound_event");
   public static final RegistryKey<Registry<Fluid>> field_239709_j_ = func_239741_a_("fluid");
   public static final RegistryKey<Registry<Effect>> field_239710_k_ = func_239741_a_("mob_effect");
   public static final RegistryKey<Registry<Block>> field_239711_l_ = func_239741_a_("block");
   public static final RegistryKey<Registry<Enchantment>> field_239712_m_ = func_239741_a_("enchantment");
   public static final RegistryKey<Registry<EntityType<?>>> field_239713_n_ = func_239741_a_("entity_type");
   public static final RegistryKey<Registry<Item>> field_239714_o_ = func_239741_a_("item");
   public static final RegistryKey<Registry<Potion>> field_239715_p_ = func_239741_a_("potion");
   public static final RegistryKey<Registry<WorldCarver<?>>> field_239716_q_ = func_239741_a_("carver");
   public static final RegistryKey<Registry<SurfaceBuilder<?>>> field_239717_r_ = func_239741_a_("surface_builder");
   public static final RegistryKey<Registry<Feature<?>>> field_239718_s_ = func_239741_a_("feature");
   public static final RegistryKey<Registry<Placement<?>>> field_239719_t_ = func_239741_a_("decorator");
   public static final RegistryKey<Registry<Biome>> field_239720_u_ = func_239741_a_("biome");
   public static final RegistryKey<Registry<BlockStateProviderType<?>>> field_239721_v_ = func_239741_a_("block_state_provider_type");
   public static final RegistryKey<Registry<BlockPlacerType<?>>> field_239722_w_ = func_239741_a_("block_placer_type");
   public static final RegistryKey<Registry<FoliagePlacerType<?>>> field_239723_x_ = func_239741_a_("foliage_placer_type");
   public static final RegistryKey<Registry<TrunkPlacerType<?>>> field_239724_y_ = func_239741_a_("trunk_placer_type");
   public static final RegistryKey<Registry<TreeDecoratorType<?>>> field_239725_z_ = func_239741_a_("tree_decorator_type");
   public static final RegistryKey<Registry<FeatureSizeType<?>>> field_239663_A_ = func_239741_a_("feature_size_type");
   public static final RegistryKey<Registry<ParticleType<?>>> field_239664_B_ = func_239741_a_("particle_type");
   public static final RegistryKey<Registry<Codec<? extends BiomeProvider>>> field_239665_C_ = func_239741_a_("biome_source");
   public static final RegistryKey<Registry<Codec<? extends ChunkGenerator>>> field_239666_D_ = func_239741_a_("chunk_generator");
   public static final RegistryKey<Registry<TileEntityType<?>>> field_239667_E_ = func_239741_a_("block_entity_type");
   public static final RegistryKey<Registry<PaintingType>> field_239668_F_ = func_239741_a_("motive");
   public static final RegistryKey<Registry<ResourceLocation>> field_239669_G_ = func_239741_a_("custom_stat");
   public static final RegistryKey<Registry<ChunkStatus>> field_239670_H_ = func_239741_a_("chunk_status");
   public static final RegistryKey<Registry<Structure<?>>> field_239671_I_ = func_239741_a_("structure_feature");
   public static final RegistryKey<Registry<IStructurePieceType>> field_239672_J_ = func_239741_a_("structure_piece");
   public static final RegistryKey<Registry<IRuleTestType<?>>> field_239673_K_ = func_239741_a_("rule_test");
   public static final RegistryKey<Registry<IPosRuleTests<?>>> field_239674_L_ = func_239741_a_("pos_rule_test");
   public static final RegistryKey<Registry<IStructureProcessorType<?>>> field_239675_M_ = func_239741_a_("structure_processor");
   public static final RegistryKey<Registry<IJigsawDeserializer<?>>> field_239676_N_ = func_239741_a_("structure_pool_element");
   public static final RegistryKey<Registry<ContainerType<?>>> field_239677_O_ = func_239741_a_("menu");
   public static final RegistryKey<Registry<IRecipeType<?>>> field_239678_P_ = func_239741_a_("recipe_type");
   public static final RegistryKey<Registry<IRecipeSerializer<?>>> field_239679_Q_ = func_239741_a_("recipe_serializer");
   public static final RegistryKey<Registry<Attribute>> field_239680_R_ = func_239741_a_("attribute");
   public static final RegistryKey<Registry<StatType<?>>> field_239681_S_ = func_239741_a_("stat_type");
   public static final RegistryKey<Registry<IVillagerType>> field_239682_T_ = func_239741_a_("villager_type");
   public static final RegistryKey<Registry<VillagerProfession>> field_239683_U_ = func_239741_a_("villager_profession");
   public static final RegistryKey<Registry<PointOfInterestType>> field_239684_V_ = func_239741_a_("point_of_interest_type");
   public static final RegistryKey<Registry<MemoryModuleType<?>>> field_239685_W_ = func_239741_a_("memory_module_type");
   public static final RegistryKey<Registry<SensorType<?>>> field_239686_X_ = func_239741_a_("sensor_type");
   public static final RegistryKey<Registry<Schedule>> field_239687_Y_ = func_239741_a_("schedule");
   public static final RegistryKey<Registry<Activity>> field_239688_Z_ = func_239741_a_("activity");
   public static final RegistryKey<Registry<LootPoolEntryType>> field_239695_aa_ = func_239741_a_("loot_pool_entry_type");
   public static final RegistryKey<Registry<LootFunctionType>> field_239696_ab_ = func_239741_a_("loot_function_type");
   public static final RegistryKey<Registry<LootConditionType>> field_239697_ac_ = func_239741_a_("loot_condition_type");
   public static final RegistryKey<Registry<DimensionType>> field_239698_ad_ = func_239741_a_("dimension_type");
   public static final RegistryKey<Registry<World>> field_239699_ae_ = func_239741_a_("dimension");
   public static final RegistryKey<Registry<Dimension>> field_239700_af_ = func_239741_a_("dimension");
   public static final Registry<SoundEvent> SOUND_EVENT = func_239746_a_(field_239708_i_, () -> {
      return SoundEvents.ENTITY_ITEM_PICKUP;
   });
   public static final DefaultedRegistry<Fluid> FLUID = func_239745_a_(field_239709_j_, "empty", () -> {
      return Fluids.EMPTY;
   });
   public static final Registry<Effect> EFFECTS = func_239746_a_(field_239710_k_, () -> {
      return Effects.LUCK;
   });
   public static final DefaultedRegistry<Block> BLOCK = func_239745_a_(field_239711_l_, "air", () -> {
      return Blocks.AIR;
   });
   public static final Registry<Enchantment> ENCHANTMENT = func_239746_a_(field_239712_m_, () -> {
      return Enchantments.FORTUNE;
   });
   public static final DefaultedRegistry<EntityType<?>> ENTITY_TYPE = func_239745_a_(field_239713_n_, "pig", () -> {
      return EntityType.PIG;
   });
   public static final DefaultedRegistry<Item> ITEM = func_239745_a_(field_239714_o_, "air", () -> {
      return Items.AIR;
   });
   public static final DefaultedRegistry<Potion> POTION = func_239745_a_(field_239715_p_, "empty", () -> {
      return Potions.EMPTY;
   });
   public static final Registry<WorldCarver<?>> CARVER = func_239746_a_(field_239716_q_, () -> {
      return WorldCarver.CAVE;
   });
   public static final Registry<SurfaceBuilder<?>> SURFACE_BUILDER = func_239746_a_(field_239717_r_, () -> {
      return SurfaceBuilder.DEFAULT;
   });
   public static final Registry<Feature<?>> FEATURE = func_239746_a_(field_239718_s_, () -> {
      return Feature.ORE;
   });
   public static final Registry<Placement<?>> DECORATOR = func_239746_a_(field_239719_t_, () -> {
      return Placement.NOPE;
   });
   public static final Registry<Biome> BIOME = func_239746_a_(field_239720_u_, () -> {
      return Biomes.DEFAULT;
   });
   public static final Registry<BlockStateProviderType<?>> BLOCK_STATE_PROVIDER_TYPE = func_239746_a_(field_239721_v_, () -> {
      return BlockStateProviderType.SIMPLE_STATE_PROVIDER;
   });
   public static final Registry<BlockPlacerType<?>> BLOCK_PLACER_TYPE = func_239746_a_(field_239722_w_, () -> {
      return BlockPlacerType.SIMPLE_BLOCK;
   });
   public static final Registry<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPE = func_239746_a_(field_239723_x_, () -> {
      return FoliagePlacerType.BLOB;
   });
   public static final Registry<TrunkPlacerType<?>> field_239701_aw_ = func_239746_a_(field_239724_y_, () -> {
      return TrunkPlacerType.field_236920_a_;
   });
   public static final Registry<TreeDecoratorType<?>> TREE_DECORATOR_TYPE = func_239746_a_(field_239725_z_, () -> {
      return TreeDecoratorType.LEAVE_VINE;
   });
   public static final Registry<FeatureSizeType<?>> field_239702_ay_ = func_239746_a_(field_239663_A_, () -> {
      return FeatureSizeType.field_236711_a_;
   });
   public static final Registry<ParticleType<?>> PARTICLE_TYPE = func_239746_a_(field_239664_B_, () -> {
      return ParticleTypes.BLOCK;
   });
   public static final Registry<Codec<? extends BiomeProvider>> field_239689_aA_ = func_239742_a_(field_239665_C_, Lifecycle.stable(), () -> {
      return BiomeProvider.field_235202_a_;
   });
   public static final Registry<Codec<? extends ChunkGenerator>> field_239690_aB_ = func_239742_a_(field_239666_D_, Lifecycle.stable(), () -> {
      return ChunkGenerator.field_235948_a_;
   });
   public static final Registry<TileEntityType<?>> BLOCK_ENTITY_TYPE = func_239746_a_(field_239667_E_, () -> {
      return TileEntityType.FURNACE;
   });
   public static final DefaultedRegistry<PaintingType> MOTIVE = func_239745_a_(field_239668_F_, "kebab", () -> {
      return PaintingType.KEBAB;
   });
   public static final Registry<ResourceLocation> CUSTOM_STAT = func_239746_a_(field_239669_G_, () -> {
      return Stats.JUMP;
   });
   public static final DefaultedRegistry<ChunkStatus> CHUNK_STATUS = func_239745_a_(field_239670_H_, "empty", () -> {
      return ChunkStatus.EMPTY;
   });
   public static final Registry<Structure<?>> STRUCTURE_FEATURE = func_239746_a_(field_239671_I_, () -> {
      return Structure.field_236367_c_;
   });
   public static final Registry<IStructurePieceType> STRUCTURE_PIECE = func_239746_a_(field_239672_J_, () -> {
      return IStructurePieceType.MSROOM;
   });
   public static final Registry<IRuleTestType<?>> RULE_TEST = func_239746_a_(field_239673_K_, () -> {
      return IRuleTestType.ALWAYS_TRUE;
   });
   public static final Registry<IPosRuleTests<?>> field_239691_aJ_ = func_239746_a_(field_239674_L_, () -> {
      return IPosRuleTests.field_237103_a_;
   });
   public static final Registry<IStructureProcessorType<?>> STRUCTURE_PROCESSOR = func_239746_a_(field_239675_M_, () -> {
      return IStructureProcessorType.BLOCK_IGNORE;
   });
   public static final Registry<IJigsawDeserializer<?>> STRUCTURE_POOL_ELEMENT = func_239746_a_(field_239676_N_, () -> {
      return IJigsawDeserializer.EMPTY_POOL_ELEMENT;
   });
   public static final Registry<ContainerType<?>> MENU = func_239746_a_(field_239677_O_, () -> {
      return ContainerType.ANVIL;
   });
   public static final Registry<IRecipeType<?>> RECIPE_TYPE = func_239746_a_(field_239678_P_, () -> {
      return IRecipeType.CRAFTING;
   });
   public static final Registry<IRecipeSerializer<?>> RECIPE_SERIALIZER = func_239746_a_(field_239679_Q_, () -> {
      return IRecipeSerializer.CRAFTING_SHAPELESS;
   });
   public static final Registry<Attribute> field_239692_aP_ = func_239746_a_(field_239680_R_, () -> {
      return Attributes.field_233828_k_;
   });
   public static final Registry<StatType<?>> STATS = func_239746_a_(field_239681_S_, () -> {
      return Stats.ITEM_USED;
   });
   public static final DefaultedRegistry<IVillagerType> VILLAGER_TYPE = func_239745_a_(field_239682_T_, "plains", () -> {
      return IVillagerType.PLAINS;
   });
   public static final DefaultedRegistry<VillagerProfession> VILLAGER_PROFESSION = func_239745_a_(field_239683_U_, "none", () -> {
      return VillagerProfession.NONE;
   });
   public static final DefaultedRegistry<PointOfInterestType> POINT_OF_INTEREST_TYPE = func_239745_a_(field_239684_V_, "unemployed", () -> {
      return PointOfInterestType.UNEMPLOYED;
   });
   public static final DefaultedRegistry<MemoryModuleType<?>> MEMORY_MODULE_TYPE = func_239745_a_(field_239685_W_, "dummy", () -> {
      return MemoryModuleType.DUMMY;
   });
   public static final DefaultedRegistry<SensorType<?>> SENSOR_TYPE = func_239745_a_(field_239686_X_, "dummy", () -> {
      return SensorType.DUMMY;
   });
   public static final Registry<Schedule> SCHEDULE = func_239746_a_(field_239687_Y_, () -> {
      return Schedule.EMPTY;
   });
   public static final Registry<Activity> ACTIVITY = func_239746_a_(field_239688_Z_, () -> {
      return Activity.IDLE;
   });
   public static final Registry<LootPoolEntryType> field_239693_aY_ = func_239746_a_(field_239695_aa_, () -> {
      return LootEntryManager.field_237410_a_;
   });
   public static final Registry<LootFunctionType> field_239694_aZ_ = func_239746_a_(field_239696_ab_, () -> {
      return LootFunctionManager.field_237429_b_;
   });
   public static final Registry<LootConditionType> field_239704_ba_ = func_239746_a_(field_239697_ac_, () -> {
      return LootConditionManager.field_237458_a_;
   });
   private final RegistryKey<Registry<T>> field_239703_b_;
   private final Lifecycle field_239705_c_;

   private static <T> RegistryKey<Registry<T>> func_239741_a_(String p_239741_0_) {
      return RegistryKey.func_240904_a_(new ResourceLocation(p_239741_0_));
   }

   private static <T extends MutableRegistry<?>> void func_239738_a_(MutableRegistry<T> p_239738_0_) {
      p_239738_0_.forEach((p_239739_1_) -> {
         if (p_239739_1_.keySet().isEmpty()) {
            LOGGER.error("Registry '{}' was empty after loading", (Object)p_239738_0_.getKey(p_239739_1_));
            if (SharedConstants.developmentMode) {
               throw new IllegalStateException("Registry: '" + p_239738_0_.getKey(p_239739_1_) + "' is empty, not allowed, fix me!");
            }
         }

         if (p_239739_1_ instanceof DefaultedRegistry) {
            ResourceLocation resourcelocation = ((DefaultedRegistry)p_239739_1_).getDefaultKey();
            Validate.notNull(p_239739_1_.getOrDefault(resourcelocation), "Missing default of DefaultedMappedRegistry: " + resourcelocation);
         }

      });
   }

   private static <T> Registry<T> func_239746_a_(RegistryKey<Registry<T>> p_239746_0_, Supplier<T> p_239746_1_) {
      return func_239742_a_(p_239746_0_, Lifecycle.experimental(), p_239746_1_);
   }

   private static <T> DefaultedRegistry<T> func_239745_a_(RegistryKey<Registry<T>> p_239745_0_, String p_239745_1_, Supplier<T> p_239745_2_) {
      return func_239744_a_(p_239745_0_, p_239745_1_, Lifecycle.experimental(), p_239745_2_);
   }

   private static <T> Registry<T> func_239742_a_(RegistryKey<Registry<T>> p_239742_0_, Lifecycle p_239742_1_, Supplier<T> p_239742_2_) {
      return func_239743_a_(p_239742_0_, new SimpleRegistry<>(p_239742_0_, p_239742_1_), p_239742_2_);
   }

   private static <T> DefaultedRegistry<T> func_239744_a_(RegistryKey<Registry<T>> p_239744_0_, String p_239744_1_, Lifecycle p_239744_2_, Supplier<T> p_239744_3_) {
      return func_239743_a_(p_239744_0_, new DefaultedRegistry<>(p_239744_1_, p_239744_0_, p_239744_2_), p_239744_3_);
   }

   private static <T, R extends MutableRegistry<T>> R func_239743_a_(RegistryKey<Registry<T>> p_239743_0_, R p_239743_1_, Supplier<T> p_239743_2_) {
      ResourceLocation resourcelocation = p_239743_0_.func_240901_a_();
      LOCATION_TO_SUPPLIER.put(resourcelocation, p_239743_2_);
      return (R)((MutableRegistry)field_239707_g_).register((RegistryKey)p_239743_0_, (Object)p_239743_1_);
   }

   protected Registry(RegistryKey<Registry<T>> p_i232510_1_, Lifecycle p_i232510_2_) {
      this.field_239703_b_ = p_i232510_1_;
      this.field_239705_c_ = p_i232510_2_;
   }

   public String toString() {
      return "Registry[" + this.field_239703_b_ + " (" + this.field_239705_c_ + ")]";
   }

   public <U> DataResult<Pair<T, U>> decode(DynamicOps<U> p_decode_1_, U p_decode_2_) {
      return p_decode_1_.compressMaps() ? p_decode_1_.getNumberValue(p_decode_2_).flatMap((p_239740_1_) -> {
         int i = p_239740_1_.intValue();
         if (!this.func_230518_b_(i)) {
            return DataResult.error("Unknown registry id: " + p_239740_1_);
         } else {
            T t = this.getByValue(i);
            return DataResult.success(t, this.field_239705_c_);
         }
      }).map((p_239736_1_) -> {
         return Pair.of(p_239736_1_, p_decode_1_.empty());
      }) : ResourceLocation.field_240908_a_.decode(p_decode_1_, p_decode_2_).addLifecycle(this.field_239705_c_).flatMap((p_239735_1_) -> {
         return !this.containsKey(p_239735_1_.getFirst()) ? DataResult.error("Unknown registry key: " + p_239735_1_.getFirst()) : DataResult.success(p_239735_1_.mapFirst(this::getOrDefault), this.field_239705_c_);
      });
   }

   public <U> DataResult<U> encode(T p_encode_1_, DynamicOps<U> p_encode_2_, U p_encode_3_) {
      ResourceLocation resourcelocation = this.getKey(p_encode_1_);
      if (resourcelocation == null) {
         return DataResult.error("Unknown registry element " + p_encode_1_);
      } else {
         return p_encode_2_.compressMaps() ? p_encode_2_.mergeToPrimitive(p_encode_3_, p_encode_2_.createInt(this.getId(p_encode_1_))).setLifecycle(this.field_239705_c_) : p_encode_2_.mergeToPrimitive(p_encode_3_, p_encode_2_.createString(resourcelocation.toString())).setLifecycle(this.field_239705_c_);
      }
   }

   public <U> Stream<U> keys(DynamicOps<U> p_keys_1_) {
      return this.keySet().stream().map((p_239737_1_) -> {
         return p_keys_1_.createString(p_239737_1_.toString());
      });
   }

   @Nullable
   public abstract ResourceLocation getKey(T value);

   public abstract Optional<RegistryKey<T>> func_230519_c_(T p_230519_1_);

   public abstract int getId(@Nullable T value);

   @Nullable
   public abstract T func_230516_a_(@Nullable RegistryKey<T> p_230516_1_);

   @Nullable
   public abstract T getOrDefault(@Nullable ResourceLocation name);

   public abstract Optional<T> getValue(@Nullable ResourceLocation key);

   public abstract Set<ResourceLocation> keySet();

   public Stream<T> stream() {
      return StreamSupport.stream(this.spliterator(), false);
   }

   public abstract boolean containsKey(ResourceLocation name);

   public abstract boolean func_239660_c_(RegistryKey<T> p_239660_1_);

   public abstract boolean func_230518_b_(int p_230518_1_);

   public static <T> T register(Registry<? super T> p_218325_0_, String p_218325_1_, T p_218325_2_) {
      return register(p_218325_0_, new ResourceLocation(p_218325_1_), p_218325_2_);
   }

   public static <V, T extends V> T register(Registry<V> p_218322_0_, ResourceLocation p_218322_1_, T p_218322_2_) {
      return ((MutableRegistry<V>)p_218322_0_).register(RegistryKey.func_240903_a_(p_218322_0_.field_239703_b_, p_218322_1_), p_218322_2_);
   }

   public static <V, T extends V> T register(Registry<V> p_218343_0_, int p_218343_1_, String p_218343_2_, T p_218343_3_) {
      return ((MutableRegistry<V>)p_218343_0_).register(p_218343_1_, RegistryKey.func_240903_a_(p_218343_0_.field_239703_b_, new ResourceLocation(p_218343_2_)), p_218343_3_);
   }

   static {
      LOCATION_TO_SUPPLIER.forEach((p_239747_0_, p_239747_1_) -> {
         if (p_239747_1_.get() == null) {
            LOGGER.error("Unable to bootstrap registry '{}'", (Object)p_239747_0_);
         }

      });
      func_239738_a_(field_239707_g_);
   }
}