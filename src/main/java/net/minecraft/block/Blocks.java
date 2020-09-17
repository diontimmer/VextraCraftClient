package net.minecraft.block;

import java.util.function.ToIntFunction;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.trees.AcaciaTree;
import net.minecraft.block.trees.BirchTree;
import net.minecraft.block.trees.DarkOakTree;
import net.minecraft.block.trees.JungleTree;
import net.minecraft.block.trees.OakTree;
import net.minecraft.block.trees.SpruceTree;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.DyeColor;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ShulkerBoxTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusConfig;

public class Blocks {
   public static final Block AIR = register("air", new AirBlock(AbstractBlock.Properties.create(Material.AIR).doesNotBlockMovement().noDrops().func_235859_g_()));
   public static final Block STONE = register("stone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block GRANITE = register("granite", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.DIRT).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block POLISHED_GRANITE = register("polished_granite", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.DIRT).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block DIORITE = register("diorite", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.QUARTZ).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block POLISHED_DIORITE = register("polished_diorite", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.QUARTZ).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block ANDESITE = register("andesite", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block POLISHED_ANDESITE = register("polished_andesite", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block GRASS_BLOCK = register("grass_block", new GrassBlock(AbstractBlock.Properties.create(Material.ORGANIC).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));
   public static final Block DIRT = register("dirt", new Block(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));
   public static final Block COARSE_DIRT = register("coarse_dirt", new Block(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));
   public static final Block PODZOL = register("podzol", new SnowyDirtBlock(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.OBSIDIAN).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));
   public static final Block COBBLESTONE = register("cobblestone", new Block(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block OAK_PLANKS = register("oak_planks", new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block SPRUCE_PLANKS = register("spruce_planks", new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block BIRCH_PLANKS = register("birch_planks", new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.SAND).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block JUNGLE_PLANKS = register("jungle_planks", new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block ACACIA_PLANKS = register("acacia_planks", new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ADOBE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block DARK_OAK_PLANKS = register("dark_oak_planks", new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block OAK_SAPLING = register("oak_sapling", new SaplingBlock(new OakTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block SPRUCE_SAPLING = register("spruce_sapling", new SaplingBlock(new SpruceTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block BIRCH_SAPLING = register("birch_sapling", new SaplingBlock(new BirchTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block JUNGLE_SAPLING = register("jungle_sapling", new SaplingBlock(new JungleTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block ACACIA_SAPLING = register("acacia_sapling", new SaplingBlock(new AcaciaTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block DARK_OAK_SAPLING = register("dark_oak_sapling", new SaplingBlock(new DarkOakTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block BEDROCK = register("bedrock", new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(-1.0F, 3600000.0F).noDrops().func_235827_a_(Blocks::func_235427_a_)));
   public static final Block WATER = register("water", new FlowingFluidBlock(Fluids.WATER, AbstractBlock.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));
   public static final Block LAVA = register("lava", new FlowingFluidBlock(Fluids.LAVA, AbstractBlock.Properties.create(Material.LAVA).doesNotBlockMovement().tickRandomly().hardnessAndResistance(100.0F).func_235838_a_((p_235418_0_) -> {
      return 15;
   }).noDrops()));
   public static final Block SAND = register("sand", new SandBlock(14406560, AbstractBlock.Properties.create(Material.SAND, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block RED_SAND = register("red_sand", new SandBlock(11098145, AbstractBlock.Properties.create(Material.SAND, MaterialColor.ADOBE).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block GRAVEL = register("gravel", new GravelBlock(AbstractBlock.Properties.create(Material.SAND, MaterialColor.STONE).hardnessAndResistance(0.6F).sound(SoundType.GROUND)));
   public static final Block GOLD_ORE = register("gold_ore", new OreBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.0F, 3.0F)));
   public static final Block IRON_ORE = register("iron_ore", new OreBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.0F, 3.0F)));
   public static final Block COAL_ORE = register("coal_ore", new OreBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.0F, 3.0F)));
   public static final Block field_235334_I_ = register("nether_gold_ore", new OreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).func_235861_h_().hardnessAndResistance(3.0F, 3.0F).sound(SoundType.field_235598_T_)));
   public static final Block OAK_LOG = register("oak_log", func_235430_a_(MaterialColor.WOOD, MaterialColor.OBSIDIAN));
   public static final Block SPRUCE_LOG = register("spruce_log", func_235430_a_(MaterialColor.OBSIDIAN, MaterialColor.BROWN));
   public static final Block BIRCH_LOG = register("birch_log", func_235430_a_(MaterialColor.SAND, MaterialColor.QUARTZ));
   public static final Block JUNGLE_LOG = register("jungle_log", func_235430_a_(MaterialColor.DIRT, MaterialColor.OBSIDIAN));
   public static final Block ACACIA_LOG = register("acacia_log", func_235430_a_(MaterialColor.ADOBE, MaterialColor.STONE));
   public static final Block DARK_OAK_LOG = register("dark_oak_log", func_235430_a_(MaterialColor.BROWN, MaterialColor.BROWN));
   public static final Block STRIPPED_SPRUCE_LOG = register("stripped_spruce_log", func_235430_a_(MaterialColor.OBSIDIAN, MaterialColor.OBSIDIAN));
   public static final Block STRIPPED_BIRCH_LOG = register("stripped_birch_log", func_235430_a_(MaterialColor.SAND, MaterialColor.SAND));
   public static final Block STRIPPED_JUNGLE_LOG = register("stripped_jungle_log", func_235430_a_(MaterialColor.DIRT, MaterialColor.DIRT));
   public static final Block STRIPPED_ACACIA_LOG = register("stripped_acacia_log", func_235430_a_(MaterialColor.ADOBE, MaterialColor.ADOBE));
   public static final Block STRIPPED_DARK_OAK_LOG = register("stripped_dark_oak_log", func_235430_a_(MaterialColor.BROWN, MaterialColor.BROWN));
   public static final Block STRIPPED_OAK_LOG = register("stripped_oak_log", func_235430_a_(MaterialColor.WOOD, MaterialColor.WOOD));
   public static final Block OAK_WOOD = register("oak_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block SPRUCE_WOOD = register("spruce_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block BIRCH_WOOD = register("birch_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.SAND).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block JUNGLE_WOOD = register("jungle_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block ACACIA_WOOD = register("acacia_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.GRAY).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block DARK_OAK_WOOD = register("dark_oak_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block STRIPPED_OAK_WOOD = register("stripped_oak_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block STRIPPED_SPRUCE_WOOD = register("stripped_spruce_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block STRIPPED_BIRCH_WOOD = register("stripped_birch_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.SAND).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block STRIPPED_JUNGLE_WOOD = register("stripped_jungle_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block STRIPPED_ACACIA_WOOD = register("stripped_acacia_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ADOBE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block STRIPPED_DARK_OAK_WOOD = register("stripped_dark_oak_wood", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
   public static final Block OAK_LEAVES = register("oak_leaves", func_235433_b_());
   public static final Block SPRUCE_LEAVES = register("spruce_leaves", func_235433_b_());
   public static final Block BIRCH_LEAVES = register("birch_leaves", func_235433_b_());
   public static final Block JUNGLE_LEAVES = register("jungle_leaves", func_235433_b_());
   public static final Block ACACIA_LEAVES = register("acacia_leaves", func_235433_b_());
   public static final Block DARK_OAK_LEAVES = register("dark_oak_leaves", func_235433_b_());
   public static final Block SPONGE = register("sponge", new SpongeBlock(AbstractBlock.Properties.create(Material.SPONGE).hardnessAndResistance(0.6F).sound(SoundType.PLANT)));
   public static final Block WET_SPONGE = register("wet_sponge", new WetSpongeBlock(AbstractBlock.Properties.create(Material.SPONGE).hardnessAndResistance(0.6F).sound(SoundType.PLANT)));
   public static final Block GLASS = register("glass", new GlassBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().func_235827_a_(Blocks::func_235427_a_).func_235828_a_(Blocks::func_235436_b_).func_235842_b_(Blocks::func_235436_b_).func_235847_c_(Blocks::func_235436_b_)));
   public static final Block LAPIS_ORE = register("lapis_ore", new OreBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.0F, 3.0F)));
   public static final Block LAPIS_BLOCK = register("lapis_block", new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.LAPIS).func_235861_h_().hardnessAndResistance(3.0F, 3.0F)));
   public static final Block DISPENSER = register("dispenser", new DispenserBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.5F)));
   public static final Block SANDSTONE = register("sandstone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).func_235861_h_().hardnessAndResistance(0.8F)));
   public static final Block CHISELED_SANDSTONE = register("chiseled_sandstone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).func_235861_h_().hardnessAndResistance(0.8F)));
   public static final Block CUT_SANDSTONE = register("cut_sandstone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).func_235861_h_().hardnessAndResistance(0.8F)));
   public static final Block NOTE_BLOCK = register("note_block", new NoteBlock(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.8F)));
   public static final Block WHITE_BED = register("white_bed", func_235422_a_(DyeColor.WHITE));
   public static final Block ORANGE_BED = register("orange_bed", func_235422_a_(DyeColor.ORANGE));
   public static final Block MAGENTA_BED = register("magenta_bed", func_235422_a_(DyeColor.MAGENTA));
   public static final Block LIGHT_BLUE_BED = register("light_blue_bed", func_235422_a_(DyeColor.LIGHT_BLUE));
   public static final Block YELLOW_BED = register("yellow_bed", func_235422_a_(DyeColor.YELLOW));
   public static final Block LIME_BED = register("lime_bed", func_235422_a_(DyeColor.LIME));
   public static final Block PINK_BED = register("pink_bed", func_235422_a_(DyeColor.PINK));
   public static final Block GRAY_BED = register("gray_bed", func_235422_a_(DyeColor.GRAY));
   public static final Block LIGHT_GRAY_BED = register("light_gray_bed", func_235422_a_(DyeColor.LIGHT_GRAY));
   public static final Block CYAN_BED = register("cyan_bed", func_235422_a_(DyeColor.CYAN));
   public static final Block PURPLE_BED = register("purple_bed", func_235422_a_(DyeColor.PURPLE));
   public static final Block BLUE_BED = register("blue_bed", func_235422_a_(DyeColor.BLUE));
   public static final Block BROWN_BED = register("brown_bed", func_235422_a_(DyeColor.BROWN));
   public static final Block GREEN_BED = register("green_bed", func_235422_a_(DyeColor.GREEN));
   public static final Block RED_BED = register("red_bed", func_235422_a_(DyeColor.RED));
   public static final Block BLACK_BED = register("black_bed", func_235422_a_(DyeColor.BLACK));
   public static final Block POWERED_RAIL = register("powered_rail", new PoweredRailBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL)));
   public static final Block DETECTOR_RAIL = register("detector_rail", new DetectorRailBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL)));
   public static final Block STICKY_PISTON = register("sticky_piston", func_235432_a_(true));
   public static final Block COBWEB = register("cobweb", new WebBlock(AbstractBlock.Properties.create(Material.WEB).doesNotBlockMovement().func_235861_h_().hardnessAndResistance(4.0F)));
   public static final Block GRASS = register("grass", new TallGrassBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block FERN = register("fern", new TallGrassBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block DEAD_BUSH = register("dead_bush", new DeadBushBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS, MaterialColor.WOOD).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block SEAGRASS = register("seagrass", new SeaGrassBlock(AbstractBlock.Properties.create(Material.SEA_GRASS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block TALL_SEAGRASS = register("tall_seagrass", new TallSeaGrassBlock(AbstractBlock.Properties.create(Material.SEA_GRASS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block PISTON = register("piston", func_235432_a_(false));
   public static final Block PISTON_HEAD = register("piston_head", new PistonHeadBlock(AbstractBlock.Properties.create(Material.PISTON).hardnessAndResistance(1.5F).noDrops()));
   public static final Block WHITE_WOOL = register("white_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.SNOW).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block ORANGE_WOOL = register("orange_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.ADOBE).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block MAGENTA_WOOL = register("magenta_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.MAGENTA).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block LIGHT_BLUE_WOOL = register("light_blue_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.LIGHT_BLUE).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block YELLOW_WOOL = register("yellow_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.YELLOW).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block LIME_WOOL = register("lime_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.LIME).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block PINK_WOOL = register("pink_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.PINK).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block GRAY_WOOL = register("gray_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.GRAY).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block LIGHT_GRAY_WOOL = register("light_gray_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.LIGHT_GRAY).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block CYAN_WOOL = register("cyan_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.CYAN).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block PURPLE_WOOL = register("purple_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.PURPLE).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block BLUE_WOOL = register("blue_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.BLUE).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block BROWN_WOOL = register("brown_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.BROWN).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block GREEN_WOOL = register("green_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.GREEN).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block RED_WOOL = register("red_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.RED).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block BLACK_WOOL = register("black_wool", new Block(AbstractBlock.Properties.create(Material.WOOL, MaterialColor.BLACK).hardnessAndResistance(0.8F).sound(SoundType.CLOTH)));
   public static final Block MOVING_PISTON = register("moving_piston", new MovingPistonBlock(AbstractBlock.Properties.create(Material.PISTON).hardnessAndResistance(-1.0F).variableOpacity().noDrops().notSolid().func_235828_a_(Blocks::func_235436_b_).func_235842_b_(Blocks::func_235436_b_).func_235847_c_(Blocks::func_235436_b_)));
   public static final Block DANDELION = register("dandelion", new FlowerBlock(Effects.SATURATION, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block POPPY = register("poppy", new FlowerBlock(Effects.NIGHT_VISION, 5, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block BLUE_ORCHID = register("blue_orchid", new FlowerBlock(Effects.SATURATION, 7, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block ALLIUM = register("allium", new FlowerBlock(Effects.FIRE_RESISTANCE, 4, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block AZURE_BLUET = register("azure_bluet", new FlowerBlock(Effects.BLINDNESS, 8, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block RED_TULIP = register("red_tulip", new FlowerBlock(Effects.WEAKNESS, 9, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block ORANGE_TULIP = register("orange_tulip", new FlowerBlock(Effects.WEAKNESS, 9, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block WHITE_TULIP = register("white_tulip", new FlowerBlock(Effects.WEAKNESS, 9, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block PINK_TULIP = register("pink_tulip", new FlowerBlock(Effects.WEAKNESS, 9, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block OXEYE_DAISY = register("oxeye_daisy", new FlowerBlock(Effects.REGENERATION, 8, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block CORNFLOWER = register("cornflower", new FlowerBlock(Effects.JUMP_BOOST, 6, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block WITHER_ROSE = register("wither_rose", new WitherRoseBlock(Effects.WITHER, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block LILY_OF_THE_VALLEY = register("lily_of_the_valley", new FlowerBlock(Effects.POISON, 12, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block BROWN_MUSHROOM = register("brown_mushroom", new MushroomBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.BROWN).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT).func_235838_a_((p_235417_0_) -> {
      return 1;
   }).func_235852_d_(Blocks::func_235426_a_)));
   public static final Block RED_MUSHROOM = register("red_mushroom", new MushroomBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.RED).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT).func_235852_d_(Blocks::func_235426_a_)));
   public static final Block GOLD_BLOCK = register("gold_block", new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GOLD).func_235861_h_().hardnessAndResistance(3.0F, 6.0F).sound(SoundType.METAL)));
   public static final Block IRON_BLOCK = register("iron_block", new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.IRON).func_235861_h_().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
   public static final Block BRICKS = register("bricks", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.RED).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block TNT = register("tnt", new TNTBlock(AbstractBlock.Properties.create(Material.TNT).zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block BOOKSHELF = register("bookshelf", new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(1.5F).sound(SoundType.WOOD)));
   public static final Block MOSSY_COBBLESTONE = register("mossy_cobblestone", new Block(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block OBSIDIAN = register("obsidian", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).func_235861_h_().hardnessAndResistance(50.0F, 1200.0F)));
   public static final Block TORCH = register("torch", new TorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().func_235838_a_((p_235470_0_) -> {
      return 14;
   }).sound(SoundType.WOOD), ParticleTypes.FLAME));
   public static final Block WALL_TORCH = register("wall_torch", new WallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().func_235838_a_((p_235469_0_) -> {
      return 14;
   }).sound(SoundType.WOOD).lootFrom(TORCH), ParticleTypes.FLAME));
   public static final Block FIRE = register("fire", new FireBlock(AbstractBlock.Properties.create(Material.FIRE, MaterialColor.TNT).doesNotBlockMovement().zeroHardnessAndResistance().func_235838_a_((p_235468_0_) -> {
      return 15;
   }).sound(SoundType.CLOTH)));
   public static final Block field_235335_bO_ = register("soul_fire", new SoulFireBlock(AbstractBlock.Properties.create(Material.FIRE, MaterialColor.LIGHT_BLUE).doesNotBlockMovement().zeroHardnessAndResistance().func_235838_a_((p_235467_0_) -> {
      return 10;
   }).sound(SoundType.CLOTH)));
   public static final Block SPAWNER = register("spawner", new SpawnerBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(5.0F).sound(SoundType.METAL).notSolid()));
   public static final Block OAK_STAIRS = register("oak_stairs", new StairsBlock(OAK_PLANKS.getDefaultState(), AbstractBlock.Properties.from(OAK_PLANKS)));
   public static final Block CHEST = register("chest", new ChestBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD), () -> {
      return TileEntityType.CHEST;
   }));
   public static final Block REDSTONE_WIRE = register("redstone_wire", new RedstoneWireBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance()));
   public static final Block DIAMOND_ORE = register("diamond_ore", new OreBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.0F, 3.0F)));
   public static final Block DIAMOND_BLOCK = register("diamond_block", new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.DIAMOND).func_235861_h_().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
   public static final Block CRAFTING_TABLE = register("crafting_table", new CraftingTableBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
   public static final Block WHEAT = register("wheat", new CropsBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP)));
   public static final Block FARMLAND = register("farmland", new FarmlandBlock(AbstractBlock.Properties.create(Material.EARTH).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.GROUND).func_235847_c_(Blocks::func_235426_a_)));
   public static final Block FURNACE = register("furnace", new FurnaceBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.5F).func_235838_a_(func_235420_a_(13))));
   public static final Block OAK_SIGN = register("oak_sign", new StandingSignBlock(AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WoodType.OAK));
   public static final Block SPRUCE_SIGN = register("spruce_sign", new StandingSignBlock(AbstractBlock.Properties.create(Material.WOOD, SPRUCE_LOG.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WoodType.SPRUCE));
   public static final Block BIRCH_SIGN = register("birch_sign", new StandingSignBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.SAND).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WoodType.BIRCH));
   public static final Block ACACIA_SIGN = register("acacia_sign", new StandingSignBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ADOBE).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WoodType.ACACIA));
   public static final Block JUNGLE_SIGN = register("jungle_sign", new StandingSignBlock(AbstractBlock.Properties.create(Material.WOOD, JUNGLE_LOG.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WoodType.JUNGLE));
   public static final Block DARK_OAK_SIGN = register("dark_oak_sign", new StandingSignBlock(AbstractBlock.Properties.create(Material.WOOD, DARK_OAK_LOG.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WoodType.DARK_OAK));
   public static final Block OAK_DOOR = register("oak_door", new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, OAK_PLANKS.func_235697_s_()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
   public static final Block LADDER = register("ladder", new LadderBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.4F).sound(SoundType.LADDER).notSolid()));
   public static final Block RAIL = register("rail", new RailBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL)));
   public static final Block COBBLESTONE_STAIRS = register("cobblestone_stairs", new StairsBlock(COBBLESTONE.getDefaultState(), AbstractBlock.Properties.from(COBBLESTONE)));
   public static final Block OAK_WALL_SIGN = register("oak_wall_sign", new WallSignBlock(AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(OAK_SIGN), WoodType.OAK));
   public static final Block SPRUCE_WALL_SIGN = register("spruce_wall_sign", new WallSignBlock(AbstractBlock.Properties.create(Material.WOOD, SPRUCE_LOG.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(SPRUCE_SIGN), WoodType.SPRUCE));
   public static final Block BIRCH_WALL_SIGN = register("birch_wall_sign", new WallSignBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.SAND).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(BIRCH_SIGN), WoodType.BIRCH));
   public static final Block ACACIA_WALL_SIGN = register("acacia_wall_sign", new WallSignBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ADOBE).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(ACACIA_SIGN), WoodType.ACACIA));
   public static final Block JUNGLE_WALL_SIGN = register("jungle_wall_sign", new WallSignBlock(AbstractBlock.Properties.create(Material.WOOD, JUNGLE_LOG.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(JUNGLE_SIGN), WoodType.JUNGLE));
   public static final Block DARK_OAK_WALL_SIGN = register("dark_oak_wall_sign", new WallSignBlock(AbstractBlock.Properties.create(Material.WOOD, DARK_OAK_LOG.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(DARK_OAK_SIGN), WoodType.DARK_OAK));
   public static final Block LEVER = register("lever", new LeverBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block STONE_PRESSURE_PLATE = register("stone_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().doesNotBlockMovement().hardnessAndResistance(0.5F)));
   public static final Block IRON_DOOR = register("iron_door", new DoorBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.IRON).func_235861_h_().hardnessAndResistance(5.0F).sound(SoundType.METAL).notSolid()));
   public static final Block OAK_PRESSURE_PLATE = register("oak_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, OAK_PLANKS.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block SPRUCE_PRESSURE_PLATE = register("spruce_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, SPRUCE_PLANKS.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block BIRCH_PRESSURE_PLATE = register("birch_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, BIRCH_PLANKS.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block JUNGLE_PRESSURE_PLATE = register("jungle_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, JUNGLE_PLANKS.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block ACACIA_PRESSURE_PLATE = register("acacia_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, ACACIA_PLANKS.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block DARK_OAK_PRESSURE_PLATE = register("dark_oak_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, DARK_OAK_PLANKS.func_235697_s_()).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block REDSTONE_ORE = register("redstone_ore", new RedstoneOreBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().tickRandomly().func_235838_a_(func_235420_a_(9)).hardnessAndResistance(3.0F, 3.0F)));
   public static final Block REDSTONE_TORCH = register("redstone_torch", new RedstoneTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().func_235838_a_(func_235420_a_(7)).sound(SoundType.WOOD)));
   public static final Block REDSTONE_WALL_TORCH = register("redstone_wall_torch", new RedstoneWallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().func_235838_a_(func_235420_a_(7)).sound(SoundType.WOOD).lootFrom(REDSTONE_TORCH)));
   public static final Block STONE_BUTTON = register("stone_button", new StoneButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F)));
   public static final Block SNOW = register("snow", new SnowBlock(AbstractBlock.Properties.create(Material.SNOW).tickRandomly().hardnessAndResistance(0.1F).func_235861_h_().sound(SoundType.SNOW)));
   public static final Block ICE = register("ice", new IceBlock(AbstractBlock.Properties.create(Material.ICE).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.GLASS).notSolid().func_235827_a_((p_235450_0_, p_235450_1_, p_235450_2_, p_235450_3_) -> {
      return p_235450_3_ == EntityType.POLAR_BEAR;
   })));
   public static final Block SNOW_BLOCK = register("snow_block", new Block(AbstractBlock.Properties.create(Material.SNOW_BLOCK).func_235861_h_().hardnessAndResistance(0.2F).sound(SoundType.SNOW)));
   public static final Block CACTUS = register("cactus", new CactusBlock(AbstractBlock.Properties.create(Material.CACTUS).tickRandomly().hardnessAndResistance(0.4F).sound(SoundType.CLOTH)));
   public static final Block CLAY = register("clay", new Block(AbstractBlock.Properties.create(Material.CLAY).hardnessAndResistance(0.6F).sound(SoundType.GROUND)));
   public static final Block SUGAR_CANE = register("sugar_cane", new SugarCaneBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block JUKEBOX = register("jukebox", new JukeboxBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(2.0F, 6.0F)));
   public static final Block OAK_FENCE = register("oak_fence", new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, OAK_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block PUMPKIN = register("pumpkin", new PumpkinBlock(AbstractBlock.Properties.create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block NETHERRACK = register("netherrack", new NetherrackBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).func_235861_h_().hardnessAndResistance(0.4F).sound(SoundType.field_235589_K_)));
   public static final Block SOUL_SAND = register("soul_sand", new SoulSandBlock(AbstractBlock.Properties.create(Material.SAND, MaterialColor.BROWN).hardnessAndResistance(0.5F).speedFactor(0.4F).sound(SoundType.field_235585_G_).func_235827_a_(Blocks::func_235437_b_).func_235828_a_(Blocks::func_235426_a_).func_235847_c_(Blocks::func_235426_a_)));
   public static final Block field_235336_cN_ = register("soul_soil", new Block(AbstractBlock.Properties.create(Material.EARTH, MaterialColor.BROWN).hardnessAndResistance(0.5F).sound(SoundType.field_235586_H_)));
   public static final Block field_235337_cO_ = register("basalt", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).func_235861_h_().hardnessAndResistance(1.25F, 4.2F).sound(SoundType.field_235587_I_)));
   public static final Block field_235338_cP_ = register("polished_basalt", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).func_235861_h_().hardnessAndResistance(1.25F, 4.2F).sound(SoundType.field_235587_I_)));
   public static final Block field_235339_cQ_ = register("soul_torch", new TorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().func_235838_a_((p_235466_0_) -> {
      return 10;
   }).sound(SoundType.WOOD), ParticleTypes.field_239811_B_));
   public static final Block field_235340_cR_ = register("soul_wall_torch", new WallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().func_235838_a_((p_235465_0_) -> {
      return 10;
   }).sound(SoundType.WOOD).lootFrom(field_235339_cQ_), ParticleTypes.field_239811_B_));
   public static final Block GLOWSTONE = register("glowstone", new Block(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.SAND).hardnessAndResistance(0.3F).sound(SoundType.GLASS).func_235838_a_((p_235464_0_) -> {
      return 15;
   })));
   public static final Block NETHER_PORTAL = register("nether_portal", new NetherPortalBlock(AbstractBlock.Properties.create(Material.PORTAL).doesNotBlockMovement().tickRandomly().hardnessAndResistance(-1.0F).sound(SoundType.GLASS).func_235838_a_((p_235463_0_) -> {
      return 11;
   })));
   public static final Block CARVED_PUMPKIN = register("carved_pumpkin", new CarvedPumpkinBlock(AbstractBlock.Properties.create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD).func_235827_a_(Blocks::func_235437_b_)));
   public static final Block JACK_O_LANTERN = register("jack_o_lantern", new CarvedPumpkinBlock(AbstractBlock.Properties.create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD).func_235838_a_((p_235462_0_) -> {
      return 15;
   }).func_235827_a_(Blocks::func_235437_b_)));
   public static final Block CAKE = register("cake", new CakeBlock(AbstractBlock.Properties.create(Material.CAKE).hardnessAndResistance(0.5F).sound(SoundType.CLOTH)));
   public static final Block REPEATER = register("repeater", new RepeaterBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().sound(SoundType.WOOD)));
   public static final Block WHITE_STAINED_GLASS = register("white_stained_glass", func_235434_b_(DyeColor.WHITE));
   public static final Block ORANGE_STAINED_GLASS = register("orange_stained_glass", func_235434_b_(DyeColor.ORANGE));
   public static final Block MAGENTA_STAINED_GLASS = register("magenta_stained_glass", func_235434_b_(DyeColor.MAGENTA));
   public static final Block LIGHT_BLUE_STAINED_GLASS = register("light_blue_stained_glass", func_235434_b_(DyeColor.LIGHT_BLUE));
   public static final Block YELLOW_STAINED_GLASS = register("yellow_stained_glass", func_235434_b_(DyeColor.YELLOW));
   public static final Block LIME_STAINED_GLASS = register("lime_stained_glass", func_235434_b_(DyeColor.LIME));
   public static final Block PINK_STAINED_GLASS = register("pink_stained_glass", func_235434_b_(DyeColor.PINK));
   public static final Block GRAY_STAINED_GLASS = register("gray_stained_glass", func_235434_b_(DyeColor.GRAY));
   public static final Block LIGHT_GRAY_STAINED_GLASS = register("light_gray_stained_glass", func_235434_b_(DyeColor.LIGHT_GRAY));
   public static final Block CYAN_STAINED_GLASS = register("cyan_stained_glass", func_235434_b_(DyeColor.CYAN));
   public static final Block PURPLE_STAINED_GLASS = register("purple_stained_glass", func_235434_b_(DyeColor.PURPLE));
   public static final Block BLUE_STAINED_GLASS = register("blue_stained_glass", func_235434_b_(DyeColor.BLUE));
   public static final Block BROWN_STAINED_GLASS = register("brown_stained_glass", func_235434_b_(DyeColor.BROWN));
   public static final Block GREEN_STAINED_GLASS = register("green_stained_glass", func_235434_b_(DyeColor.GREEN));
   public static final Block RED_STAINED_GLASS = register("red_stained_glass", func_235434_b_(DyeColor.RED));
   public static final Block BLACK_STAINED_GLASS = register("black_stained_glass", func_235434_b_(DyeColor.BLACK));
   public static final Block OAK_TRAPDOOR = register("oak_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().func_235827_a_(Blocks::func_235427_a_)));
   public static final Block SPRUCE_TRAPDOOR = register("spruce_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().func_235827_a_(Blocks::func_235427_a_)));
   public static final Block BIRCH_TRAPDOOR = register("birch_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.SAND).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().func_235827_a_(Blocks::func_235427_a_)));
   public static final Block JUNGLE_TRAPDOOR = register("jungle_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().func_235827_a_(Blocks::func_235427_a_)));
   public static final Block ACACIA_TRAPDOOR = register("acacia_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ADOBE).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().func_235827_a_(Blocks::func_235427_a_)));
   public static final Block DARK_OAK_TRAPDOOR = register("dark_oak_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().func_235827_a_(Blocks::func_235427_a_)));
   public static final Block STONE_BRICKS = register("stone_bricks", new Block(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block MOSSY_STONE_BRICKS = register("mossy_stone_bricks", new Block(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block CRACKED_STONE_BRICKS = register("cracked_stone_bricks", new Block(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block CHISELED_STONE_BRICKS = register("chiseled_stone_bricks", new Block(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block INFESTED_STONE = register("infested_stone", new SilverfishBlock(STONE, AbstractBlock.Properties.create(Material.CLAY).hardnessAndResistance(0.0F, 0.75F)));
   public static final Block INFESTED_COBBLESTONE = register("infested_cobblestone", new SilverfishBlock(COBBLESTONE, AbstractBlock.Properties.create(Material.CLAY).hardnessAndResistance(0.0F, 0.75F)));
   public static final Block INFESTED_STONE_BRICKS = register("infested_stone_bricks", new SilverfishBlock(STONE_BRICKS, AbstractBlock.Properties.create(Material.CLAY).hardnessAndResistance(0.0F, 0.75F)));
   public static final Block INFESTED_MOSSY_STONE_BRICKS = register("infested_mossy_stone_bricks", new SilverfishBlock(MOSSY_STONE_BRICKS, AbstractBlock.Properties.create(Material.CLAY).hardnessAndResistance(0.0F, 0.75F)));
   public static final Block INFESTED_CRACKED_STONE_BRICKS = register("infested_cracked_stone_bricks", new SilverfishBlock(CRACKED_STONE_BRICKS, AbstractBlock.Properties.create(Material.CLAY).hardnessAndResistance(0.0F, 0.75F)));
   public static final Block INFESTED_CHISELED_STONE_BRICKS = register("infested_chiseled_stone_bricks", new SilverfishBlock(CHISELED_STONE_BRICKS, AbstractBlock.Properties.create(Material.CLAY).hardnessAndResistance(0.0F, 0.75F)));
   public static final Block BROWN_MUSHROOM_BLOCK = register("brown_mushroom_block", new HugeMushroomBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(0.2F).sound(SoundType.WOOD)));
   public static final Block RED_MUSHROOM_BLOCK = register("red_mushroom_block", new HugeMushroomBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(0.2F).sound(SoundType.WOOD)));
   public static final Block MUSHROOM_STEM = register("mushroom_stem", new HugeMushroomBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOL).hardnessAndResistance(0.2F).sound(SoundType.WOOD)));
   public static final Block IRON_BARS = register("iron_bars", new PaneBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.AIR).func_235861_h_().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).notSolid()));
   public static final Block field_235341_dI_ = register("chain", new ChainBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.AIR).func_235861_h_().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.field_235597_S_).notSolid()));
   public static final Block GLASS_PANE = register("glass_pane", new PaneBlock(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block MELON = register("melon", new MelonBlock(AbstractBlock.Properties.create(Material.GOURD, MaterialColor.LIME).hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block ATTACHED_PUMPKIN_STEM = register("attached_pumpkin_stem", new AttachedStemBlock((StemGrownBlock)PUMPKIN, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WOOD)));
   public static final Block ATTACHED_MELON_STEM = register("attached_melon_stem", new AttachedStemBlock((StemGrownBlock)MELON, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WOOD)));
   public static final Block PUMPKIN_STEM = register("pumpkin_stem", new StemBlock((StemGrownBlock)PUMPKIN, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.STEM)));
   public static final Block MELON_STEM = register("melon_stem", new StemBlock((StemGrownBlock)MELON, AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.STEM)));
   public static final Block VINE = register("vine", new VineBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.2F).sound(SoundType.field_235601_w_)));
   public static final Block OAK_FENCE_GATE = register("oak_fence_gate", new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, OAK_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block BRICK_STAIRS = register("brick_stairs", new StairsBlock(BRICKS.getDefaultState(), AbstractBlock.Properties.from(BRICKS)));
   public static final Block STONE_BRICK_STAIRS = register("stone_brick_stairs", new StairsBlock(STONE_BRICKS.getDefaultState(), AbstractBlock.Properties.from(STONE_BRICKS)));
   public static final Block MYCELIUM = register("mycelium", new MyceliumBlock(AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.PURPLE).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.PLANT)));
   public static final Block LILY_PAD = register("lily_pad", new LilyPadBlock(AbstractBlock.Properties.create(Material.PLANTS).zeroHardnessAndResistance().sound(SoundType.field_235600_d_).notSolid()));
   public static final Block NETHER_BRICKS = register("nether_bricks", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).func_235861_h_().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.field_235590_L_)));
   public static final Block NETHER_BRICK_FENCE = register("nether_brick_fence", new FenceBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).func_235861_h_().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.field_235590_L_)));
   public static final Block NETHER_BRICK_STAIRS = register("nether_brick_stairs", new StairsBlock(NETHER_BRICKS.getDefaultState(), AbstractBlock.Properties.from(NETHER_BRICKS)));
   public static final Block NETHER_WART = register("nether_wart", new NetherWartBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.RED).doesNotBlockMovement().tickRandomly().sound(SoundType.NETHER_WART)));
   public static final Block ENCHANTING_TABLE = register("enchanting_table", new EnchantingTableBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.RED).func_235861_h_().hardnessAndResistance(5.0F, 1200.0F)));
   public static final Block BREWING_STAND = register("brewing_stand", new BrewingStandBlock(AbstractBlock.Properties.create(Material.IRON).func_235861_h_().hardnessAndResistance(0.5F).func_235838_a_((p_235461_0_) -> {
      return 1;
   }).notSolid()));
   public static final Block CAULDRON = register("cauldron", new CauldronBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.STONE).func_235861_h_().hardnessAndResistance(2.0F).notSolid()));
   public static final Block END_PORTAL = register("end_portal", new EndPortalBlock(AbstractBlock.Properties.create(Material.PORTAL, MaterialColor.BLACK).doesNotBlockMovement().func_235838_a_((p_235460_0_) -> {
      return 15;
   }).hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
   public static final Block END_PORTAL_FRAME = register("end_portal_frame", new EndPortalFrameBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GREEN).sound(SoundType.GLASS).func_235838_a_((p_235459_0_) -> {
      return 1;
   }).hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
   public static final Block END_STONE = register("end_stone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).func_235861_h_().hardnessAndResistance(3.0F, 9.0F)));
   public static final Block DRAGON_EGG = register("dragon_egg", new DragonEggBlock(AbstractBlock.Properties.create(Material.DRAGON_EGG, MaterialColor.BLACK).hardnessAndResistance(3.0F, 9.0F).func_235838_a_((p_235458_0_) -> {
      return 1;
   }).notSolid()));
   public static final Block REDSTONE_LAMP = register("redstone_lamp", new RedstoneLampBlock(AbstractBlock.Properties.create(Material.REDSTONE_LIGHT).func_235838_a_(func_235420_a_(15)).hardnessAndResistance(0.3F).sound(SoundType.GLASS).func_235827_a_(Blocks::func_235437_b_)));
   public static final Block COCOA = register("cocoa", new CocoaBlock(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().hardnessAndResistance(0.2F, 3.0F).sound(SoundType.WOOD).notSolid()));
   public static final Block SANDSTONE_STAIRS = register("sandstone_stairs", new StairsBlock(SANDSTONE.getDefaultState(), AbstractBlock.Properties.from(SANDSTONE)));
   public static final Block EMERALD_ORE = register("emerald_ore", new OreBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.0F, 3.0F)));
   public static final Block ENDER_CHEST = register("ender_chest", new EnderChestBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(22.5F, 600.0F).func_235838_a_((p_235457_0_) -> {
      return 7;
   })));
   public static final Block TRIPWIRE_HOOK = register("tripwire_hook", new TripWireHookBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()));
   public static final Block TRIPWIRE = register("tripwire", new TripWireBlock((TripWireHookBlock)TRIPWIRE_HOOK, AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()));
   public static final Block EMERALD_BLOCK = register("emerald_block", new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.EMERALD).func_235861_h_().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));
   public static final Block SPRUCE_STAIRS = register("spruce_stairs", new StairsBlock(SPRUCE_PLANKS.getDefaultState(), AbstractBlock.Properties.from(SPRUCE_PLANKS)));
   public static final Block BIRCH_STAIRS = register("birch_stairs", new StairsBlock(BIRCH_PLANKS.getDefaultState(), AbstractBlock.Properties.from(BIRCH_PLANKS)));
   public static final Block JUNGLE_STAIRS = register("jungle_stairs", new StairsBlock(JUNGLE_PLANKS.getDefaultState(), AbstractBlock.Properties.from(JUNGLE_PLANKS)));
   public static final Block COMMAND_BLOCK = register("command_block", new CommandBlockBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BROWN).func_235861_h_().hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
   public static final Block BEACON = register("beacon", new BeaconBlock(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.DIAMOND).hardnessAndResistance(3.0F).func_235838_a_((p_235456_0_) -> {
      return 15;
   }).notSolid().func_235828_a_(Blocks::func_235436_b_)));
   public static final Block COBBLESTONE_WALL = register("cobblestone_wall", new WallBlock(AbstractBlock.Properties.from(COBBLESTONE)));
   public static final Block MOSSY_COBBLESTONE_WALL = register("mossy_cobblestone_wall", new WallBlock(AbstractBlock.Properties.from(COBBLESTONE)));
   public static final Block FLOWER_POT = register("flower_pot", new FlowerPotBlock(AIR, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_OAK_SAPLING = register("potted_oak_sapling", new FlowerPotBlock(OAK_SAPLING, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_SPRUCE_SAPLING = register("potted_spruce_sapling", new FlowerPotBlock(SPRUCE_SAPLING, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_BIRCH_SAPLING = register("potted_birch_sapling", new FlowerPotBlock(BIRCH_SAPLING, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_JUNGLE_SAPLING = register("potted_jungle_sapling", new FlowerPotBlock(JUNGLE_SAPLING, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_ACACIA_SAPLING = register("potted_acacia_sapling", new FlowerPotBlock(ACACIA_SAPLING, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_DARK_OAK_SAPLING = register("potted_dark_oak_sapling", new FlowerPotBlock(DARK_OAK_SAPLING, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_FERN = register("potted_fern", new FlowerPotBlock(FERN, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_DANDELION = register("potted_dandelion", new FlowerPotBlock(DANDELION, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_POPPY = register("potted_poppy", new FlowerPotBlock(POPPY, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_BLUE_ORCHID = register("potted_blue_orchid", new FlowerPotBlock(BLUE_ORCHID, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_ALLIUM = register("potted_allium", new FlowerPotBlock(ALLIUM, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_AZURE_BLUET = register("potted_azure_bluet", new FlowerPotBlock(AZURE_BLUET, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_RED_TULIP = register("potted_red_tulip", new FlowerPotBlock(RED_TULIP, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_ORANGE_TULIP = register("potted_orange_tulip", new FlowerPotBlock(ORANGE_TULIP, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_WHITE_TULIP = register("potted_white_tulip", new FlowerPotBlock(WHITE_TULIP, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_PINK_TULIP = register("potted_pink_tulip", new FlowerPotBlock(PINK_TULIP, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_OXEYE_DAISY = register("potted_oxeye_daisy", new FlowerPotBlock(OXEYE_DAISY, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_CORNFLOWER = register("potted_cornflower", new FlowerPotBlock(CORNFLOWER, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_LILY_OF_THE_VALLEY = register("potted_lily_of_the_valley", new FlowerPotBlock(LILY_OF_THE_VALLEY, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_WITHER_ROSE = register("potted_wither_rose", new FlowerPotBlock(WITHER_ROSE, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_RED_MUSHROOM = register("potted_red_mushroom", new FlowerPotBlock(RED_MUSHROOM, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_BROWN_MUSHROOM = register("potted_brown_mushroom", new FlowerPotBlock(BROWN_MUSHROOM, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_DEAD_BUSH = register("potted_dead_bush", new FlowerPotBlock(DEAD_BUSH, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block POTTED_CACTUS = register("potted_cactus", new FlowerPotBlock(CACTUS, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block CARROTS = register("carrots", new CarrotBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP)));
   public static final Block POTATOES = register("potatoes", new PotatoBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP)));
   public static final Block OAK_BUTTON = register("oak_button", new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block SPRUCE_BUTTON = register("spruce_button", new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block BIRCH_BUTTON = register("birch_button", new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block JUNGLE_BUTTON = register("jungle_button", new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block ACACIA_BUTTON = register("acacia_button", new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block DARK_OAK_BUTTON = register("dark_oak_button", new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block SKELETON_SKULL = register("skeleton_skull", new SkullBlock(SkullBlock.Types.SKELETON, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F)));
   public static final Block SKELETON_WALL_SKULL = register("skeleton_wall_skull", new WallSkullBlock(SkullBlock.Types.SKELETON, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F).lootFrom(SKELETON_SKULL)));
   public static final Block WITHER_SKELETON_SKULL = register("wither_skeleton_skull", new WitherSkeletonSkullBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F)));
   public static final Block WITHER_SKELETON_WALL_SKULL = register("wither_skeleton_wall_skull", new WitherSkeletonWallSkullBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F).lootFrom(WITHER_SKELETON_SKULL)));
   public static final Block ZOMBIE_HEAD = register("zombie_head", new SkullBlock(SkullBlock.Types.ZOMBIE, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F)));
   public static final Block ZOMBIE_WALL_HEAD = register("zombie_wall_head", new WallSkullBlock(SkullBlock.Types.ZOMBIE, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F).lootFrom(ZOMBIE_HEAD)));
   public static final Block PLAYER_HEAD = register("player_head", new SkullPlayerBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F)));
   public static final Block PLAYER_WALL_HEAD = register("player_wall_head", new SkullWallPlayerBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F).lootFrom(PLAYER_HEAD)));
   public static final Block CREEPER_HEAD = register("creeper_head", new SkullBlock(SkullBlock.Types.CREEPER, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F)));
   public static final Block CREEPER_WALL_HEAD = register("creeper_wall_head", new WallSkullBlock(SkullBlock.Types.CREEPER, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F).lootFrom(CREEPER_HEAD)));
   public static final Block DRAGON_HEAD = register("dragon_head", new SkullBlock(SkullBlock.Types.DRAGON, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F)));
   public static final Block DRAGON_WALL_HEAD = register("dragon_wall_head", new WallSkullBlock(SkullBlock.Types.DRAGON, AbstractBlock.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(1.0F).lootFrom(DRAGON_HEAD)));
   public static final Block ANVIL = register("anvil", new AnvilBlock(AbstractBlock.Properties.create(Material.ANVIL, MaterialColor.IRON).func_235861_h_().hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL)));
   public static final Block CHIPPED_ANVIL = register("chipped_anvil", new AnvilBlock(AbstractBlock.Properties.create(Material.ANVIL, MaterialColor.IRON).func_235861_h_().hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL)));
   public static final Block DAMAGED_ANVIL = register("damaged_anvil", new AnvilBlock(AbstractBlock.Properties.create(Material.ANVIL, MaterialColor.IRON).func_235861_h_().hardnessAndResistance(5.0F, 1200.0F).sound(SoundType.ANVIL)));
   public static final Block TRAPPED_CHEST = register("trapped_chest", new TrappedChestBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
   public static final Block LIGHT_WEIGHTED_PRESSURE_PLATE = register("light_weighted_pressure_plate", new WeightedPressurePlateBlock(15, AbstractBlock.Properties.create(Material.IRON, MaterialColor.GOLD).func_235861_h_().doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block HEAVY_WEIGHTED_PRESSURE_PLATE = register("heavy_weighted_pressure_plate", new WeightedPressurePlateBlock(150, AbstractBlock.Properties.create(Material.IRON).func_235861_h_().doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block COMPARATOR = register("comparator", new ComparatorBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().sound(SoundType.WOOD)));
   public static final Block DAYLIGHT_DETECTOR = register("daylight_detector", new DaylightDetectorBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.2F).sound(SoundType.WOOD)));
   public static final Block REDSTONE_BLOCK = register("redstone_block", new RedstoneBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.TNT).func_235861_h_().hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).func_235828_a_(Blocks::func_235436_b_)));
   public static final Block NETHER_QUARTZ_ORE = register("nether_quartz_ore", new OreBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).func_235861_h_().hardnessAndResistance(3.0F, 3.0F).sound(SoundType.field_235592_N_)));
   public static final Block HOPPER = register("hopper", new HopperBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.STONE).func_235861_h_().hardnessAndResistance(3.0F, 4.8F).sound(SoundType.METAL).notSolid()));
   public static final Block QUARTZ_BLOCK = register("quartz_block", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.QUARTZ).func_235861_h_().hardnessAndResistance(0.8F)));
   public static final Block CHISELED_QUARTZ_BLOCK = register("chiseled_quartz_block", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.QUARTZ).func_235861_h_().hardnessAndResistance(0.8F)));
   public static final Block QUARTZ_PILLAR = register("quartz_pillar", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.QUARTZ).func_235861_h_().hardnessAndResistance(0.8F)));
   public static final Block QUARTZ_STAIRS = register("quartz_stairs", new StairsBlock(QUARTZ_BLOCK.getDefaultState(), AbstractBlock.Properties.from(QUARTZ_BLOCK)));
   public static final Block ACTIVATOR_RAIL = register("activator_rail", new PoweredRailBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL)));
   public static final Block DROPPER = register("dropper", new DropperBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.5F)));
   public static final Block WHITE_TERRACOTTA = register("white_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block ORANGE_TERRACOTTA = register("orange_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ORANGE_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block MAGENTA_TERRACOTTA = register("magenta_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.MAGENTA_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block LIGHT_BLUE_TERRACOTTA = register("light_blue_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.LIGHT_BLUE_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block YELLOW_TERRACOTTA = register("yellow_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.YELLOW_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block LIME_TERRACOTTA = register("lime_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.LIME_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block PINK_TERRACOTTA = register("pink_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PINK_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block GRAY_TERRACOTTA = register("gray_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block LIGHT_GRAY_TERRACOTTA = register("light_gray_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.LIGHT_GRAY_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block CYAN_TERRACOTTA = register("cyan_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.CYAN_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block PURPLE_TERRACOTTA = register("purple_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block BLUE_TERRACOTTA = register("blue_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block BROWN_TERRACOTTA = register("brown_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BROWN_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block GREEN_TERRACOTTA = register("green_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GREEN_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block RED_TERRACOTTA = register("red_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.RED_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block BLACK_TERRACOTTA = register("black_terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK_TERRACOTTA).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block WHITE_STAINED_GLASS_PANE = register("white_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.WHITE, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block ORANGE_STAINED_GLASS_PANE = register("orange_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.ORANGE, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block MAGENTA_STAINED_GLASS_PANE = register("magenta_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.MAGENTA, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block LIGHT_BLUE_STAINED_GLASS_PANE = register("light_blue_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block YELLOW_STAINED_GLASS_PANE = register("yellow_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.YELLOW, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block LIME_STAINED_GLASS_PANE = register("lime_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.LIME, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block PINK_STAINED_GLASS_PANE = register("pink_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.PINK, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block GRAY_STAINED_GLASS_PANE = register("gray_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.GRAY, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block LIGHT_GRAY_STAINED_GLASS_PANE = register("light_gray_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block CYAN_STAINED_GLASS_PANE = register("cyan_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.CYAN, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block PURPLE_STAINED_GLASS_PANE = register("purple_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.PURPLE, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block BLUE_STAINED_GLASS_PANE = register("blue_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.BLUE, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block BROWN_STAINED_GLASS_PANE = register("brown_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.BROWN, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block GREEN_STAINED_GLASS_PANE = register("green_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.GREEN, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block RED_STAINED_GLASS_PANE = register("red_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.RED, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block BLACK_STAINED_GLASS_PANE = register("black_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.BLACK, AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
   public static final Block ACACIA_STAIRS = register("acacia_stairs", new StairsBlock(ACACIA_PLANKS.getDefaultState(), AbstractBlock.Properties.from(ACACIA_PLANKS)));
   public static final Block DARK_OAK_STAIRS = register("dark_oak_stairs", new StairsBlock(DARK_OAK_PLANKS.getDefaultState(), AbstractBlock.Properties.from(DARK_OAK_PLANKS)));
   public static final Block SLIME_BLOCK = register("slime_block", new SlimeBlock(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.GRASS).slipperiness(0.8F).sound(SoundType.SLIME).notSolid()));
   public static final Block BARRIER = register("barrier", new BarrierBlock(AbstractBlock.Properties.create(Material.BARRIER).hardnessAndResistance(-1.0F, 3600000.8F).noDrops().notSolid().func_235827_a_(Blocks::func_235427_a_)));
   public static final Block IRON_TRAPDOOR = register("iron_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create(Material.IRON).func_235861_h_().hardnessAndResistance(5.0F).sound(SoundType.METAL).notSolid()));
   public static final Block PRISMARINE = register("prismarine", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.CYAN).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block PRISMARINE_BRICKS = register("prismarine_bricks", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.DIAMOND).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block DARK_PRISMARINE = register("dark_prismarine", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.DIAMOND).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block PRISMARINE_STAIRS = register("prismarine_stairs", new StairsBlock(PRISMARINE.getDefaultState(), AbstractBlock.Properties.from(PRISMARINE)));
   public static final Block PRISMARINE_BRICK_STAIRS = register("prismarine_brick_stairs", new StairsBlock(PRISMARINE_BRICKS.getDefaultState(), AbstractBlock.Properties.from(PRISMARINE_BRICKS)));
   public static final Block DARK_PRISMARINE_STAIRS = register("dark_prismarine_stairs", new StairsBlock(DARK_PRISMARINE.getDefaultState(), AbstractBlock.Properties.from(DARK_PRISMARINE)));
   public static final Block PRISMARINE_SLAB = register("prismarine_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.CYAN).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block PRISMARINE_BRICK_SLAB = register("prismarine_brick_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.DIAMOND).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block DARK_PRISMARINE_SLAB = register("dark_prismarine_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.DIAMOND).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block SEA_LANTERN = register("sea_lantern", new Block(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.QUARTZ).hardnessAndResistance(0.3F).sound(SoundType.GLASS).func_235838_a_((p_235455_0_) -> {
      return 15;
   })));
   public static final Block HAY_BLOCK = register("hay_block", new HayBlock(AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.PLANT)));
   public static final Block WHITE_CARPET = register("white_carpet", new CarpetBlock(DyeColor.WHITE, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.SNOW).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block ORANGE_CARPET = register("orange_carpet", new CarpetBlock(DyeColor.ORANGE, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.ADOBE).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block MAGENTA_CARPET = register("magenta_carpet", new CarpetBlock(DyeColor.MAGENTA, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.MAGENTA).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block LIGHT_BLUE_CARPET = register("light_blue_carpet", new CarpetBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.LIGHT_BLUE).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block YELLOW_CARPET = register("yellow_carpet", new CarpetBlock(DyeColor.YELLOW, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.YELLOW).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block LIME_CARPET = register("lime_carpet", new CarpetBlock(DyeColor.LIME, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.LIME).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block PINK_CARPET = register("pink_carpet", new CarpetBlock(DyeColor.PINK, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.PINK).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block GRAY_CARPET = register("gray_carpet", new CarpetBlock(DyeColor.GRAY, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.GRAY).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block LIGHT_GRAY_CARPET = register("light_gray_carpet", new CarpetBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.LIGHT_GRAY).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block CYAN_CARPET = register("cyan_carpet", new CarpetBlock(DyeColor.CYAN, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.CYAN).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block PURPLE_CARPET = register("purple_carpet", new CarpetBlock(DyeColor.PURPLE, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.PURPLE).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block BLUE_CARPET = register("blue_carpet", new CarpetBlock(DyeColor.BLUE, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.BLUE).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block BROWN_CARPET = register("brown_carpet", new CarpetBlock(DyeColor.BROWN, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.BROWN).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block GREEN_CARPET = register("green_carpet", new CarpetBlock(DyeColor.GREEN, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.GREEN).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block RED_CARPET = register("red_carpet", new CarpetBlock(DyeColor.RED, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.RED).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block BLACK_CARPET = register("black_carpet", new CarpetBlock(DyeColor.BLACK, AbstractBlock.Properties.create(Material.CARPET, MaterialColor.BLACK).hardnessAndResistance(0.1F).sound(SoundType.CLOTH)));
   public static final Block TERRACOTTA = register("terracotta", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ADOBE).func_235861_h_().hardnessAndResistance(1.25F, 4.2F)));
   public static final Block COAL_BLOCK = register("coal_block", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).func_235861_h_().hardnessAndResistance(5.0F, 6.0F)));
   public static final Block PACKED_ICE = register("packed_ice", new Block(AbstractBlock.Properties.create(Material.PACKED_ICE).slipperiness(0.98F).hardnessAndResistance(0.5F).sound(SoundType.GLASS)));
   public static final Block SUNFLOWER = register("sunflower", new TallFlowerBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block LILAC = register("lilac", new TallFlowerBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block ROSE_BUSH = register("rose_bush", new TallFlowerBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block PEONY = register("peony", new TallFlowerBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block TALL_GRASS = register("tall_grass", new DoublePlantBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block LARGE_FERN = register("large_fern", new DoublePlantBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
   public static final Block WHITE_BANNER = register("white_banner", new BannerBlock(DyeColor.WHITE, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block ORANGE_BANNER = register("orange_banner", new BannerBlock(DyeColor.ORANGE, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block MAGENTA_BANNER = register("magenta_banner", new BannerBlock(DyeColor.MAGENTA, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block LIGHT_BLUE_BANNER = register("light_blue_banner", new BannerBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block YELLOW_BANNER = register("yellow_banner", new BannerBlock(DyeColor.YELLOW, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block LIME_BANNER = register("lime_banner", new BannerBlock(DyeColor.LIME, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block PINK_BANNER = register("pink_banner", new BannerBlock(DyeColor.PINK, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block GRAY_BANNER = register("gray_banner", new BannerBlock(DyeColor.GRAY, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block LIGHT_GRAY_BANNER = register("light_gray_banner", new BannerBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block CYAN_BANNER = register("cyan_banner", new BannerBlock(DyeColor.CYAN, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block PURPLE_BANNER = register("purple_banner", new BannerBlock(DyeColor.PURPLE, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block BLUE_BANNER = register("blue_banner", new BannerBlock(DyeColor.BLUE, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block BROWN_BANNER = register("brown_banner", new BannerBlock(DyeColor.BROWN, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block GREEN_BANNER = register("green_banner", new BannerBlock(DyeColor.GREEN, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block RED_BANNER = register("red_banner", new BannerBlock(DyeColor.RED, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block BLACK_BANNER = register("black_banner", new BannerBlock(DyeColor.BLACK, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
   public static final Block WHITE_WALL_BANNER = register("white_wall_banner", new WallBannerBlock(DyeColor.WHITE, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(WHITE_BANNER)));
   public static final Block ORANGE_WALL_BANNER = register("orange_wall_banner", new WallBannerBlock(DyeColor.ORANGE, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(ORANGE_BANNER)));
   public static final Block MAGENTA_WALL_BANNER = register("magenta_wall_banner", new WallBannerBlock(DyeColor.MAGENTA, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(MAGENTA_BANNER)));
   public static final Block LIGHT_BLUE_WALL_BANNER = register("light_blue_wall_banner", new WallBannerBlock(DyeColor.LIGHT_BLUE, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(LIGHT_BLUE_BANNER)));
   public static final Block YELLOW_WALL_BANNER = register("yellow_wall_banner", new WallBannerBlock(DyeColor.YELLOW, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(YELLOW_BANNER)));
   public static final Block LIME_WALL_BANNER = register("lime_wall_banner", new WallBannerBlock(DyeColor.LIME, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(LIME_BANNER)));
   public static final Block PINK_WALL_BANNER = register("pink_wall_banner", new WallBannerBlock(DyeColor.PINK, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(PINK_BANNER)));
   public static final Block GRAY_WALL_BANNER = register("gray_wall_banner", new WallBannerBlock(DyeColor.GRAY, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(GRAY_BANNER)));
   public static final Block LIGHT_GRAY_WALL_BANNER = register("light_gray_wall_banner", new WallBannerBlock(DyeColor.LIGHT_GRAY, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(LIGHT_GRAY_BANNER)));
   public static final Block CYAN_WALL_BANNER = register("cyan_wall_banner", new WallBannerBlock(DyeColor.CYAN, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(CYAN_BANNER)));
   public static final Block PURPLE_WALL_BANNER = register("purple_wall_banner", new WallBannerBlock(DyeColor.PURPLE, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(PURPLE_BANNER)));
   public static final Block BLUE_WALL_BANNER = register("blue_wall_banner", new WallBannerBlock(DyeColor.BLUE, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(BLUE_BANNER)));
   public static final Block BROWN_WALL_BANNER = register("brown_wall_banner", new WallBannerBlock(DyeColor.BROWN, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(BROWN_BANNER)));
   public static final Block GREEN_WALL_BANNER = register("green_wall_banner", new WallBannerBlock(DyeColor.GREEN, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(GREEN_BANNER)));
   public static final Block RED_WALL_BANNER = register("red_wall_banner", new WallBannerBlock(DyeColor.RED, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(RED_BANNER)));
   public static final Block BLACK_WALL_BANNER = register("black_wall_banner", new WallBannerBlock(DyeColor.BLACK, AbstractBlock.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(BLACK_BANNER)));
   public static final Block RED_SANDSTONE = register("red_sandstone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ADOBE).func_235861_h_().hardnessAndResistance(0.8F)));
   public static final Block CHISELED_RED_SANDSTONE = register("chiseled_red_sandstone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ADOBE).func_235861_h_().hardnessAndResistance(0.8F)));
   public static final Block CUT_RED_SANDSTONE = register("cut_red_sandstone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ADOBE).func_235861_h_().hardnessAndResistance(0.8F)));
   public static final Block RED_SANDSTONE_STAIRS = register("red_sandstone_stairs", new StairsBlock(RED_SANDSTONE.getDefaultState(), AbstractBlock.Properties.from(RED_SANDSTONE)));
   public static final Block OAK_SLAB = register("oak_slab", new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block SPRUCE_SLAB = register("spruce_slab", new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block BIRCH_SLAB = register("birch_slab", new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.SAND).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block JUNGLE_SLAB = register("jungle_slab", new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block ACACIA_SLAB = register("acacia_slab", new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.ADOBE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block DARK_OAK_SLAB = register("dark_oak_slab", new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block STONE_SLAB = register("stone_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block SMOOTH_STONE_SLAB = register("smooth_stone_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block SANDSTONE_SLAB = register("sandstone_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block CUT_SANDSTONE_SLAB = register("cut_sandstone_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block PETRIFIED_OAK_SLAB = register("petrified_oak_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.WOOD).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block COBBLESTONE_SLAB = register("cobblestone_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block BRICK_SLAB = register("brick_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.RED).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block STONE_BRICK_SLAB = register("stone_brick_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block NETHER_BRICK_SLAB = register("nether_brick_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).func_235861_h_().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.field_235590_L_)));
   public static final Block QUARTZ_SLAB = register("quartz_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.QUARTZ).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block RED_SANDSTONE_SLAB = register("red_sandstone_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ADOBE).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block CUT_RED_SANDSTONE_SLAB = register("cut_red_sandstone_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ADOBE).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block PURPUR_SLAB = register("purpur_slab", new SlabBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.MAGENTA).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block SMOOTH_STONE = register("smooth_stone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.STONE).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block SMOOTH_SANDSTONE = register("smooth_sandstone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block SMOOTH_QUARTZ = register("smooth_quartz", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.QUARTZ).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block SMOOTH_RED_SANDSTONE = register("smooth_red_sandstone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ADOBE).func_235861_h_().hardnessAndResistance(2.0F, 6.0F)));
   public static final Block SPRUCE_FENCE_GATE = register("spruce_fence_gate", new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, SPRUCE_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block BIRCH_FENCE_GATE = register("birch_fence_gate", new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, BIRCH_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block JUNGLE_FENCE_GATE = register("jungle_fence_gate", new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, JUNGLE_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block ACACIA_FENCE_GATE = register("acacia_fence_gate", new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, ACACIA_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block DARK_OAK_FENCE_GATE = register("dark_oak_fence_gate", new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, DARK_OAK_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block SPRUCE_FENCE = register("spruce_fence", new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, SPRUCE_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block BIRCH_FENCE = register("birch_fence", new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, BIRCH_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block JUNGLE_FENCE = register("jungle_fence", new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, JUNGLE_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block ACACIA_FENCE = register("acacia_fence", new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, ACACIA_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block DARK_OAK_FENCE = register("dark_oak_fence", new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, DARK_OAK_PLANKS.func_235697_s_()).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block SPRUCE_DOOR = register("spruce_door", new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, SPRUCE_PLANKS.func_235697_s_()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
   public static final Block BIRCH_DOOR = register("birch_door", new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, BIRCH_PLANKS.func_235697_s_()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
   public static final Block JUNGLE_DOOR = register("jungle_door", new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, JUNGLE_PLANKS.func_235697_s_()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
   public static final Block ACACIA_DOOR = register("acacia_door", new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, ACACIA_PLANKS.func_235697_s_()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
   public static final Block DARK_OAK_DOOR = register("dark_oak_door", new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, DARK_OAK_PLANKS.func_235697_s_()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
   public static final Block END_ROD = register("end_rod", new EndRodBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().func_235838_a_((p_235454_0_) -> {
      return 14;
   }).sound(SoundType.WOOD).notSolid()));
   public static final Block CHORUS_PLANT = register("chorus_plant", new ChorusPlantBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.PURPLE).hardnessAndResistance(0.4F).sound(SoundType.WOOD).notSolid()));
   public static final Block CHORUS_FLOWER = register("chorus_flower", new ChorusFlowerBlock((ChorusPlantBlock)CHORUS_PLANT, AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.PURPLE).tickRandomly().hardnessAndResistance(0.4F).sound(SoundType.WOOD).notSolid()));
   public static final Block PURPUR_BLOCK = register("purpur_block", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.MAGENTA).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block PURPUR_PILLAR = register("purpur_pillar", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.MAGENTA).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block PURPUR_STAIRS = register("purpur_stairs", new StairsBlock(PURPUR_BLOCK.getDefaultState(), AbstractBlock.Properties.from(PURPUR_BLOCK)));
   public static final Block END_STONE_BRICKS = register("end_stone_bricks", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).func_235861_h_().hardnessAndResistance(3.0F, 9.0F)));
   public static final Block BEETROOTS = register("beetroots", new BeetrootBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP)));
   public static final Block GRASS_PATH = register("grass_path", new GrassPathBlock(AbstractBlock.Properties.create(Material.EARTH).hardnessAndResistance(0.65F).sound(SoundType.PLANT).func_235847_c_(Blocks::func_235426_a_)));
   public static final Block END_GATEWAY = register("end_gateway", new EndGatewayBlock(AbstractBlock.Properties.create(Material.PORTAL, MaterialColor.BLACK).doesNotBlockMovement().func_235838_a_((p_235453_0_) -> {
      return 15;
   }).hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
   public static final Block REPEATING_COMMAND_BLOCK = register("repeating_command_block", new CommandBlockBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.PURPLE).func_235861_h_().hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
   public static final Block CHAIN_COMMAND_BLOCK = register("chain_command_block", new CommandBlockBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GREEN).func_235861_h_().hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
   public static final Block FROSTED_ICE = register("frosted_ice", new FrostedIceBlock(AbstractBlock.Properties.create(Material.ICE).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.GLASS).notSolid().func_235827_a_((p_235448_0_, p_235448_1_, p_235448_2_, p_235448_3_) -> {
      return p_235448_3_ == EntityType.POLAR_BEAR;
   })));
   public static final Block MAGMA_BLOCK = register("magma_block", new MagmaBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).func_235861_h_().func_235838_a_((p_235452_0_) -> {
      return 3;
   }).tickRandomly().hardnessAndResistance(0.5F).func_235827_a_((p_235445_0_, p_235445_1_, p_235445_2_, p_235445_3_) -> {
      return p_235445_3_.isImmuneToFire();
   }).func_235852_d_(Blocks::func_235426_a_).func_235856_e_(Blocks::func_235426_a_)));
   public static final Block NETHER_WART_BLOCK = register("nether_wart_block", new Block(AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.RED).hardnessAndResistance(1.0F).sound(SoundType.field_235588_J_)));
   public static final Block RED_NETHER_BRICKS = register("red_nether_bricks", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).func_235861_h_().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.field_235590_L_)));
   public static final Block BONE_BLOCK = register("bone_block", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.SAND).func_235861_h_().hardnessAndResistance(2.0F).sound(SoundType.field_235593_O_)));
   public static final Block STRUCTURE_VOID = register("structure_void", new StructureVoidBlock(AbstractBlock.Properties.create(Material.STRUCTURE_VOID).doesNotBlockMovement().noDrops()));
   public static final Block OBSERVER = register("observer", new ObserverBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).func_235861_h_().func_235828_a_(Blocks::func_235436_b_)));
   public static final Block SHULKER_BOX = register("shulker_box", func_235423_a_((DyeColor)null, AbstractBlock.Properties.create(Material.SHULKER)));
   public static final Block WHITE_SHULKER_BOX = register("white_shulker_box", func_235423_a_(DyeColor.WHITE, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.SNOW)));
   public static final Block ORANGE_SHULKER_BOX = register("orange_shulker_box", func_235423_a_(DyeColor.ORANGE, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.ADOBE)));
   public static final Block MAGENTA_SHULKER_BOX = register("magenta_shulker_box", func_235423_a_(DyeColor.MAGENTA, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.MAGENTA)));
   public static final Block LIGHT_BLUE_SHULKER_BOX = register("light_blue_shulker_box", func_235423_a_(DyeColor.LIGHT_BLUE, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.LIGHT_BLUE)));
   public static final Block YELLOW_SHULKER_BOX = register("yellow_shulker_box", func_235423_a_(DyeColor.YELLOW, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.YELLOW)));
   public static final Block LIME_SHULKER_BOX = register("lime_shulker_box", func_235423_a_(DyeColor.LIME, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.LIME)));
   public static final Block PINK_SHULKER_BOX = register("pink_shulker_box", func_235423_a_(DyeColor.PINK, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.PINK)));
   public static final Block GRAY_SHULKER_BOX = register("gray_shulker_box", func_235423_a_(DyeColor.GRAY, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.GRAY)));
   public static final Block LIGHT_GRAY_SHULKER_BOX = register("light_gray_shulker_box", func_235423_a_(DyeColor.LIGHT_GRAY, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.LIGHT_GRAY)));
   public static final Block CYAN_SHULKER_BOX = register("cyan_shulker_box", func_235423_a_(DyeColor.CYAN, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.CYAN)));
   public static final Block PURPLE_SHULKER_BOX = register("purple_shulker_box", func_235423_a_(DyeColor.PURPLE, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.PURPLE_TERRACOTTA)));
   public static final Block BLUE_SHULKER_BOX = register("blue_shulker_box", func_235423_a_(DyeColor.BLUE, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.BLUE)));
   public static final Block BROWN_SHULKER_BOX = register("brown_shulker_box", func_235423_a_(DyeColor.BROWN, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.BROWN)));
   public static final Block GREEN_SHULKER_BOX = register("green_shulker_box", func_235423_a_(DyeColor.GREEN, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.GREEN)));
   public static final Block RED_SHULKER_BOX = register("red_shulker_box", func_235423_a_(DyeColor.RED, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.RED)));
   public static final Block BLACK_SHULKER_BOX = register("black_shulker_box", func_235423_a_(DyeColor.BLACK, AbstractBlock.Properties.create(Material.SHULKER, MaterialColor.BLACK)));
   public static final Block WHITE_GLAZED_TERRACOTTA = register("white_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.WHITE).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block ORANGE_GLAZED_TERRACOTTA = register("orange_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.ORANGE).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block MAGENTA_GLAZED_TERRACOTTA = register("magenta_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.MAGENTA).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block LIGHT_BLUE_GLAZED_TERRACOTTA = register("light_blue_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.LIGHT_BLUE).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block YELLOW_GLAZED_TERRACOTTA = register("yellow_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.YELLOW).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block LIME_GLAZED_TERRACOTTA = register("lime_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.LIME).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block PINK_GLAZED_TERRACOTTA = register("pink_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.PINK).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block GRAY_GLAZED_TERRACOTTA = register("gray_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.GRAY).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block LIGHT_GRAY_GLAZED_TERRACOTTA = register("light_gray_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.LIGHT_GRAY).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block CYAN_GLAZED_TERRACOTTA = register("cyan_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.CYAN).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block PURPLE_GLAZED_TERRACOTTA = register("purple_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.PURPLE).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block BLUE_GLAZED_TERRACOTTA = register("blue_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.BLUE).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block BROWN_GLAZED_TERRACOTTA = register("brown_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.BROWN).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block GREEN_GLAZED_TERRACOTTA = register("green_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.GREEN).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block RED_GLAZED_TERRACOTTA = register("red_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.RED).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block BLACK_GLAZED_TERRACOTTA = register("black_glazed_terracotta", new GlazedTerracottaBlock(AbstractBlock.Properties.create(Material.ROCK, DyeColor.BLACK).func_235861_h_().hardnessAndResistance(1.4F)));
   public static final Block WHITE_CONCRETE = register("white_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.WHITE).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block ORANGE_CONCRETE = register("orange_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.ORANGE).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block MAGENTA_CONCRETE = register("magenta_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.MAGENTA).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block LIGHT_BLUE_CONCRETE = register("light_blue_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.LIGHT_BLUE).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block YELLOW_CONCRETE = register("yellow_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.YELLOW).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block LIME_CONCRETE = register("lime_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.LIME).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block PINK_CONCRETE = register("pink_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.PINK).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block GRAY_CONCRETE = register("gray_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.GRAY).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block LIGHT_GRAY_CONCRETE = register("light_gray_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.LIGHT_GRAY).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block CYAN_CONCRETE = register("cyan_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.CYAN).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block PURPLE_CONCRETE = register("purple_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.PURPLE).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block BLUE_CONCRETE = register("blue_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.BLUE).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block BROWN_CONCRETE = register("brown_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.BROWN).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block GREEN_CONCRETE = register("green_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.GREEN).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block RED_CONCRETE = register("red_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.RED).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block BLACK_CONCRETE = register("black_concrete", new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.BLACK).func_235861_h_().hardnessAndResistance(1.8F)));
   public static final Block WHITE_CONCRETE_POWDER = register("white_concrete_powder", new ConcretePowderBlock(WHITE_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.WHITE).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block ORANGE_CONCRETE_POWDER = register("orange_concrete_powder", new ConcretePowderBlock(ORANGE_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.ORANGE).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block MAGENTA_CONCRETE_POWDER = register("magenta_concrete_powder", new ConcretePowderBlock(MAGENTA_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.MAGENTA).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block LIGHT_BLUE_CONCRETE_POWDER = register("light_blue_concrete_powder", new ConcretePowderBlock(LIGHT_BLUE_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.LIGHT_BLUE).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block YELLOW_CONCRETE_POWDER = register("yellow_concrete_powder", new ConcretePowderBlock(YELLOW_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block LIME_CONCRETE_POWDER = register("lime_concrete_powder", new ConcretePowderBlock(LIME_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.LIME).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block PINK_CONCRETE_POWDER = register("pink_concrete_powder", new ConcretePowderBlock(PINK_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.PINK).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block GRAY_CONCRETE_POWDER = register("gray_concrete_powder", new ConcretePowderBlock(GRAY_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.GRAY).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block LIGHT_GRAY_CONCRETE_POWDER = register("light_gray_concrete_powder", new ConcretePowderBlock(LIGHT_GRAY_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.LIGHT_GRAY).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block CYAN_CONCRETE_POWDER = register("cyan_concrete_powder", new ConcretePowderBlock(CYAN_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.CYAN).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block PURPLE_CONCRETE_POWDER = register("purple_concrete_powder", new ConcretePowderBlock(PURPLE_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.PURPLE).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block BLUE_CONCRETE_POWDER = register("blue_concrete_powder", new ConcretePowderBlock(BLUE_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.BLUE).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block BROWN_CONCRETE_POWDER = register("brown_concrete_powder", new ConcretePowderBlock(BROWN_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.BROWN).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block GREEN_CONCRETE_POWDER = register("green_concrete_powder", new ConcretePowderBlock(GREEN_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.GREEN).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block RED_CONCRETE_POWDER = register("red_concrete_powder", new ConcretePowderBlock(RED_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.RED).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block BLACK_CONCRETE_POWDER = register("black_concrete_powder", new ConcretePowderBlock(BLACK_CONCRETE, AbstractBlock.Properties.create(Material.SAND, DyeColor.BLACK).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
   public static final Block KELP = register("kelp", new KelpTopBlock(AbstractBlock.Properties.create(Material.OCEAN_PLANT).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block KELP_PLANT = register("kelp_plant", new KelpBlock(AbstractBlock.Properties.create(Material.OCEAN_PLANT).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block DRIED_KELP_BLOCK = register("dried_kelp_block", new Block(AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.GREEN).hardnessAndResistance(0.5F, 2.5F).sound(SoundType.PLANT)));
   public static final Block TURTLE_EGG = register("turtle_egg", new TurtleEggBlock(AbstractBlock.Properties.create(Material.DRAGON_EGG, MaterialColor.SAND).hardnessAndResistance(0.5F).sound(SoundType.METAL).tickRandomly().notSolid()));
   public static final Block DEAD_TUBE_CORAL_BLOCK = register("dead_tube_coral_block", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block DEAD_BRAIN_CORAL_BLOCK = register("dead_brain_coral_block", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block DEAD_BUBBLE_CORAL_BLOCK = register("dead_bubble_coral_block", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block DEAD_FIRE_CORAL_BLOCK = register("dead_fire_coral_block", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block DEAD_HORN_CORAL_BLOCK = register("dead_horn_coral_block", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block TUBE_CORAL_BLOCK = register("tube_coral_block", new CoralBlock(DEAD_TUBE_CORAL_BLOCK, AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLUE).func_235861_h_().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)));
   public static final Block BRAIN_CORAL_BLOCK = register("brain_coral_block", new CoralBlock(DEAD_BRAIN_CORAL_BLOCK, AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PINK).func_235861_h_().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)));
   public static final Block BUBBLE_CORAL_BLOCK = register("bubble_coral_block", new CoralBlock(DEAD_BUBBLE_CORAL_BLOCK, AbstractBlock.Properties.create(Material.ROCK, MaterialColor.PURPLE).func_235861_h_().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)));
   public static final Block FIRE_CORAL_BLOCK = register("fire_coral_block", new CoralBlock(DEAD_FIRE_CORAL_BLOCK, AbstractBlock.Properties.create(Material.ROCK, MaterialColor.RED).func_235861_h_().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)));
   public static final Block HORN_CORAL_BLOCK = register("horn_coral_block", new CoralBlock(DEAD_HORN_CORAL_BLOCK, AbstractBlock.Properties.create(Material.ROCK, MaterialColor.YELLOW).func_235861_h_().hardnessAndResistance(1.5F, 6.0F).sound(SoundType.CORAL)));
   public static final Block DEAD_TUBE_CORAL = register("dead_tube_coral", new DeadCoralPlantBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance()));
   public static final Block DEAD_BRAIN_CORAL = register("dead_brain_coral", new DeadCoralPlantBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance()));
   public static final Block DEAD_BUBBLE_CORAL = register("dead_bubble_coral", new DeadCoralPlantBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance()));
   public static final Block DEAD_FIRE_CORAL = register("dead_fire_coral", new DeadCoralPlantBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance()));
   public static final Block DEAD_HORN_CORAL = register("dead_horn_coral", new DeadCoralPlantBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance()));
   public static final Block TUBE_CORAL = register("tube_coral", new CoralPlantBlock(DEAD_TUBE_CORAL, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.BLUE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block BRAIN_CORAL = register("brain_coral", new CoralPlantBlock(DEAD_BRAIN_CORAL, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.PINK).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block BUBBLE_CORAL = register("bubble_coral", new CoralPlantBlock(DEAD_BUBBLE_CORAL, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.PURPLE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block FIRE_CORAL = register("fire_coral", new CoralPlantBlock(DEAD_FIRE_CORAL, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.RED).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block HORN_CORAL = register("horn_coral", new CoralPlantBlock(DEAD_HORN_CORAL, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.YELLOW).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block DEAD_TUBE_CORAL_FAN = register("dead_tube_coral_fan", new CoralFanBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance()));
   public static final Block DEAD_BRAIN_CORAL_FAN = register("dead_brain_coral_fan", new CoralFanBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance()));
   public static final Block DEAD_BUBBLE_CORAL_FAN = register("dead_bubble_coral_fan", new CoralFanBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance()));
   public static final Block DEAD_FIRE_CORAL_FAN = register("dead_fire_coral_fan", new CoralFanBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance()));
   public static final Block DEAD_HORN_CORAL_FAN = register("dead_horn_coral_fan", new CoralFanBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance()));
   public static final Block TUBE_CORAL_FAN = register("tube_coral_fan", new CoralFinBlock(DEAD_TUBE_CORAL_FAN, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.BLUE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block BRAIN_CORAL_FAN = register("brain_coral_fan", new CoralFinBlock(DEAD_BRAIN_CORAL_FAN, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.PINK).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block BUBBLE_CORAL_FAN = register("bubble_coral_fan", new CoralFinBlock(DEAD_BUBBLE_CORAL_FAN, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.PURPLE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block FIRE_CORAL_FAN = register("fire_coral_fan", new CoralFinBlock(DEAD_FIRE_CORAL_FAN, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.RED).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block HORN_CORAL_FAN = register("horn_coral_fan", new CoralFinBlock(DEAD_HORN_CORAL_FAN, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.YELLOW).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS)));
   public static final Block DEAD_TUBE_CORAL_WALL_FAN = register("dead_tube_coral_wall_fan", new DeadCoralWallFanBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance().lootFrom(DEAD_TUBE_CORAL_FAN)));
   public static final Block DEAD_BRAIN_CORAL_WALL_FAN = register("dead_brain_coral_wall_fan", new DeadCoralWallFanBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance().lootFrom(DEAD_BRAIN_CORAL_FAN)));
   public static final Block DEAD_BUBBLE_CORAL_WALL_FAN = register("dead_bubble_coral_wall_fan", new DeadCoralWallFanBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance().lootFrom(DEAD_BUBBLE_CORAL_FAN)));
   public static final Block DEAD_FIRE_CORAL_WALL_FAN = register("dead_fire_coral_wall_fan", new DeadCoralWallFanBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance().lootFrom(DEAD_FIRE_CORAL_FAN)));
   public static final Block DEAD_HORN_CORAL_WALL_FAN = register("dead_horn_coral_wall_fan", new DeadCoralWallFanBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).func_235861_h_().doesNotBlockMovement().zeroHardnessAndResistance().lootFrom(DEAD_HORN_CORAL_FAN)));
   public static final Block TUBE_CORAL_WALL_FAN = register("tube_coral_wall_fan", new CoralWallFanBlock(DEAD_TUBE_CORAL_WALL_FAN, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.BLUE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS).lootFrom(TUBE_CORAL_FAN)));
   public static final Block BRAIN_CORAL_WALL_FAN = register("brain_coral_wall_fan", new CoralWallFanBlock(DEAD_BRAIN_CORAL_WALL_FAN, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.PINK).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS).lootFrom(BRAIN_CORAL_FAN)));
   public static final Block BUBBLE_CORAL_WALL_FAN = register("bubble_coral_wall_fan", new CoralWallFanBlock(DEAD_BUBBLE_CORAL_WALL_FAN, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.PURPLE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS).lootFrom(BUBBLE_CORAL_FAN)));
   public static final Block FIRE_CORAL_WALL_FAN = register("fire_coral_wall_fan", new CoralWallFanBlock(DEAD_FIRE_CORAL_WALL_FAN, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.RED).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS).lootFrom(FIRE_CORAL_FAN)));
   public static final Block HORN_CORAL_WALL_FAN = register("horn_coral_wall_fan", new CoralWallFanBlock(DEAD_HORN_CORAL_WALL_FAN, AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.YELLOW).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WET_GRASS).lootFrom(HORN_CORAL_FAN)));
   public static final Block SEA_PICKLE = register("sea_pickle", new SeaPickleBlock(AbstractBlock.Properties.create(Material.OCEAN_PLANT, MaterialColor.GREEN).func_235838_a_((p_235451_0_) -> {
      return SeaPickleBlock.isInBadEnvironment(p_235451_0_) ? 0 : 3 + 3 * p_235451_0_.get(SeaPickleBlock.PICKLES);
   }).sound(SoundType.SLIME).notSolid()));
   public static final Block BLUE_ICE = register("blue_ice", new BreakableBlock(AbstractBlock.Properties.create(Material.PACKED_ICE).hardnessAndResistance(2.8F).slipperiness(0.989F).sound(SoundType.GLASS)));
   public static final Block CONDUIT = register("conduit", new ConduitBlock(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.DIAMOND).hardnessAndResistance(3.0F).func_235838_a_((p_235449_0_) -> {
      return 15;
   }).notSolid()));
   public static final Block BAMBOO_SAPLING = register("bamboo_sapling", new BambooSaplingBlock(AbstractBlock.Properties.create(Material.BAMBOO_SAPLING).tickRandomly().zeroHardnessAndResistance().doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.BAMBOO_SAPLING)));
   public static final Block BAMBOO = register("bamboo", new BambooBlock(AbstractBlock.Properties.create(Material.BAMBOO, MaterialColor.FOLIAGE).tickRandomly().zeroHardnessAndResistance().hardnessAndResistance(1.0F).sound(SoundType.BAMBOO).notSolid()));
   public static final Block POTTED_BAMBOO = register("potted_bamboo", new FlowerPotBlock(BAMBOO, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block VOID_AIR = register("void_air", new AirBlock(AbstractBlock.Properties.create(Material.AIR).doesNotBlockMovement().noDrops().func_235859_g_()));
   public static final Block CAVE_AIR = register("cave_air", new AirBlock(AbstractBlock.Properties.create(Material.AIR).doesNotBlockMovement().noDrops().func_235859_g_()));
   public static final Block BUBBLE_COLUMN = register("bubble_column", new BubbleColumnBlock(AbstractBlock.Properties.create(Material.BUBBLE_COLUMN).doesNotBlockMovement().noDrops()));
   public static final Block POLISHED_GRANITE_STAIRS = register("polished_granite_stairs", new StairsBlock(POLISHED_GRANITE.getDefaultState(), AbstractBlock.Properties.from(POLISHED_GRANITE)));
   public static final Block SMOOTH_RED_SANDSTONE_STAIRS = register("smooth_red_sandstone_stairs", new StairsBlock(SMOOTH_RED_SANDSTONE.getDefaultState(), AbstractBlock.Properties.from(SMOOTH_RED_SANDSTONE)));
   public static final Block MOSSY_STONE_BRICK_STAIRS = register("mossy_stone_brick_stairs", new StairsBlock(MOSSY_STONE_BRICKS.getDefaultState(), AbstractBlock.Properties.from(MOSSY_STONE_BRICKS)));
   public static final Block POLISHED_DIORITE_STAIRS = register("polished_diorite_stairs", new StairsBlock(POLISHED_DIORITE.getDefaultState(), AbstractBlock.Properties.from(POLISHED_DIORITE)));
   public static final Block MOSSY_COBBLESTONE_STAIRS = register("mossy_cobblestone_stairs", new StairsBlock(MOSSY_COBBLESTONE.getDefaultState(), AbstractBlock.Properties.from(MOSSY_COBBLESTONE)));
   public static final Block END_STONE_BRICK_STAIRS = register("end_stone_brick_stairs", new StairsBlock(END_STONE_BRICKS.getDefaultState(), AbstractBlock.Properties.from(END_STONE_BRICKS)));
   public static final Block STONE_STAIRS = register("stone_stairs", new StairsBlock(STONE.getDefaultState(), AbstractBlock.Properties.from(STONE)));
   public static final Block SMOOTH_SANDSTONE_STAIRS = register("smooth_sandstone_stairs", new StairsBlock(SMOOTH_SANDSTONE.getDefaultState(), AbstractBlock.Properties.from(SMOOTH_SANDSTONE)));
   public static final Block SMOOTH_QUARTZ_STAIRS = register("smooth_quartz_stairs", new StairsBlock(SMOOTH_QUARTZ.getDefaultState(), AbstractBlock.Properties.from(SMOOTH_QUARTZ)));
   public static final Block GRANITE_STAIRS = register("granite_stairs", new StairsBlock(GRANITE.getDefaultState(), AbstractBlock.Properties.from(GRANITE)));
   public static final Block ANDESITE_STAIRS = register("andesite_stairs", new StairsBlock(ANDESITE.getDefaultState(), AbstractBlock.Properties.from(ANDESITE)));
   public static final Block RED_NETHER_BRICK_STAIRS = register("red_nether_brick_stairs", new StairsBlock(RED_NETHER_BRICKS.getDefaultState(), AbstractBlock.Properties.from(RED_NETHER_BRICKS)));
   public static final Block POLISHED_ANDESITE_STAIRS = register("polished_andesite_stairs", new StairsBlock(POLISHED_ANDESITE.getDefaultState(), AbstractBlock.Properties.from(POLISHED_ANDESITE)));
   public static final Block DIORITE_STAIRS = register("diorite_stairs", new StairsBlock(DIORITE.getDefaultState(), AbstractBlock.Properties.from(DIORITE)));
   public static final Block POLISHED_GRANITE_SLAB = register("polished_granite_slab", new SlabBlock(AbstractBlock.Properties.from(POLISHED_GRANITE)));
   public static final Block SMOOTH_RED_SANDSTONE_SLAB = register("smooth_red_sandstone_slab", new SlabBlock(AbstractBlock.Properties.from(SMOOTH_RED_SANDSTONE)));
   public static final Block MOSSY_STONE_BRICK_SLAB = register("mossy_stone_brick_slab", new SlabBlock(AbstractBlock.Properties.from(MOSSY_STONE_BRICKS)));
   public static final Block POLISHED_DIORITE_SLAB = register("polished_diorite_slab", new SlabBlock(AbstractBlock.Properties.from(POLISHED_DIORITE)));
   public static final Block MOSSY_COBBLESTONE_SLAB = register("mossy_cobblestone_slab", new SlabBlock(AbstractBlock.Properties.from(MOSSY_COBBLESTONE)));
   public static final Block END_STONE_BRICK_SLAB = register("end_stone_brick_slab", new SlabBlock(AbstractBlock.Properties.from(END_STONE_BRICKS)));
   public static final Block SMOOTH_SANDSTONE_SLAB = register("smooth_sandstone_slab", new SlabBlock(AbstractBlock.Properties.from(SMOOTH_SANDSTONE)));
   public static final Block SMOOTH_QUARTZ_SLAB = register("smooth_quartz_slab", new SlabBlock(AbstractBlock.Properties.from(SMOOTH_QUARTZ)));
   public static final Block GRANITE_SLAB = register("granite_slab", new SlabBlock(AbstractBlock.Properties.from(GRANITE)));
   public static final Block ANDESITE_SLAB = register("andesite_slab", new SlabBlock(AbstractBlock.Properties.from(ANDESITE)));
   public static final Block RED_NETHER_BRICK_SLAB = register("red_nether_brick_slab", new SlabBlock(AbstractBlock.Properties.from(RED_NETHER_BRICKS)));
   public static final Block POLISHED_ANDESITE_SLAB = register("polished_andesite_slab", new SlabBlock(AbstractBlock.Properties.from(POLISHED_ANDESITE)));
   public static final Block DIORITE_SLAB = register("diorite_slab", new SlabBlock(AbstractBlock.Properties.from(DIORITE)));
   public static final Block BRICK_WALL = register("brick_wall", new WallBlock(AbstractBlock.Properties.from(BRICKS)));
   public static final Block PRISMARINE_WALL = register("prismarine_wall", new WallBlock(AbstractBlock.Properties.from(PRISMARINE)));
   public static final Block RED_SANDSTONE_WALL = register("red_sandstone_wall", new WallBlock(AbstractBlock.Properties.from(RED_SANDSTONE)));
   public static final Block MOSSY_STONE_BRICK_WALL = register("mossy_stone_brick_wall", new WallBlock(AbstractBlock.Properties.from(MOSSY_STONE_BRICKS)));
   public static final Block GRANITE_WALL = register("granite_wall", new WallBlock(AbstractBlock.Properties.from(GRANITE)));
   public static final Block STONE_BRICK_WALL = register("stone_brick_wall", new WallBlock(AbstractBlock.Properties.from(STONE_BRICKS)));
   public static final Block NETHER_BRICK_WALL = register("nether_brick_wall", new WallBlock(AbstractBlock.Properties.from(NETHER_BRICKS)));
   public static final Block ANDESITE_WALL = register("andesite_wall", new WallBlock(AbstractBlock.Properties.from(ANDESITE)));
   public static final Block RED_NETHER_BRICK_WALL = register("red_nether_brick_wall", new WallBlock(AbstractBlock.Properties.from(RED_NETHER_BRICKS)));
   public static final Block SANDSTONE_WALL = register("sandstone_wall", new WallBlock(AbstractBlock.Properties.from(SANDSTONE)));
   public static final Block END_STONE_BRICK_WALL = register("end_stone_brick_wall", new WallBlock(AbstractBlock.Properties.from(END_STONE_BRICKS)));
   public static final Block DIORITE_WALL = register("diorite_wall", new WallBlock(AbstractBlock.Properties.from(DIORITE)));
   public static final Block SCAFFOLDING = register("scaffolding", new ScaffoldingBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS, MaterialColor.SAND).doesNotBlockMovement().sound(SoundType.SCAFFOLDING).variableOpacity()));
   public static final Block LOOM = register("loom", new LoomBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
   public static final Block BARREL = register("barrel", new BarrelBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
   public static final Block SMOKER = register("smoker", new SmokerBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.5F).func_235838_a_(func_235420_a_(13))));
   public static final Block BLAST_FURNACE = register("blast_furnace", new BlastFurnaceBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.5F).func_235838_a_(func_235420_a_(13))));
   public static final Block CARTOGRAPHY_TABLE = register("cartography_table", new CartographyTableBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
   public static final Block FLETCHING_TABLE = register("fletching_table", new FletchingTableBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
   public static final Block GRINDSTONE = register("grindstone", new GrindstoneBlock(AbstractBlock.Properties.create(Material.ANVIL, MaterialColor.IRON).func_235861_h_().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));
   public static final Block LECTERN = register("lectern", new LecternBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
   public static final Block SMITHING_TABLE = register("smithing_table", new SmithingTableBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)));
   public static final Block STONECUTTER = register("stonecutter", new StonecutterBlock(AbstractBlock.Properties.create(Material.ROCK).func_235861_h_().hardnessAndResistance(3.5F)));
   public static final Block BELL = register("bell", new BellBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.GOLD).func_235861_h_().hardnessAndResistance(5.0F).sound(SoundType.ANVIL)));
   public static final Block LANTERN = register("lantern", new LanternBlock(AbstractBlock.Properties.create(Material.IRON).func_235861_h_().hardnessAndResistance(3.5F).sound(SoundType.LANTERN).func_235838_a_((p_235447_0_) -> {
      return 15;
   }).notSolid()));
   public static final Block field_235366_md_ = register("soul_lantern", new LanternBlock(AbstractBlock.Properties.create(Material.IRON).func_235861_h_().hardnessAndResistance(3.5F).sound(SoundType.LANTERN).func_235838_a_((p_235443_0_) -> {
      return 10;
   }).notSolid()));
   public static final Block CAMPFIRE = register("campfire", new CampfireBlock(true, 1, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD).func_235838_a_(func_235420_a_(15)).notSolid()));
   public static final Block field_235367_mf_ = register("soul_campfire", new CampfireBlock(false, 2, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD).func_235838_a_(func_235420_a_(10)).notSolid()));
   public static final Block SWEET_BERRY_BUSH = register("sweet_berry_bush", new SweetBerryBushBlock(AbstractBlock.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH)));
   public static final Block field_235368_mh_ = register("warped_stem", func_235428_a_(MaterialColor.field_241543_af_));
   public static final Block field_235369_mi_ = register("stripped_warped_stem", func_235428_a_(MaterialColor.field_241543_af_));
   public static final Block field_235370_mj_ = register("warped_hyphae", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.field_241544_ag_).hardnessAndResistance(2.0F).sound(SoundType.field_235602_z_)));
   public static final Block field_235371_mk_ = register("stripped_warped_hyphae", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.field_241544_ag_).hardnessAndResistance(2.0F).sound(SoundType.field_235602_z_)));
   public static final Block field_235372_ml_ = register("warped_nylium", new NyliumBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.field_241542_ae_).func_235861_h_().hardnessAndResistance(0.4F).sound(SoundType.field_235579_A_).tickRandomly()));
   public static final Block field_235373_mm_ = register("warped_fungus", new FungusBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.CYAN).zeroHardnessAndResistance().doesNotBlockMovement().sound(SoundType.field_235580_B_), () -> {
      return Feature.field_236281_L_.withConfiguration(HugeFungusConfig.field_236301_d_);
   }));
   public static final Block field_235374_mn_ = register("warped_wart_block", new Block(AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.field_241545_ah_).hardnessAndResistance(1.0F).sound(SoundType.field_235588_J_)));
   public static final Block field_235375_mo_ = register("warped_roots", new NetherRootsBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS, MaterialColor.CYAN).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.field_235581_C_)));
   public static final Block field_235376_mp_ = register("nether_sprouts", new NetherSproutsBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS, MaterialColor.CYAN).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.field_235591_M_)));
   public static final Block field_235377_mq_ = register("crimson_stem", func_235428_a_(MaterialColor.field_241540_ac_));
   public static final Block field_235378_mr_ = register("stripped_crimson_stem", func_235428_a_(MaterialColor.field_241540_ac_));
   public static final Block field_235379_ms_ = register("crimson_hyphae", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.field_241541_ad_).hardnessAndResistance(2.0F).sound(SoundType.field_235602_z_)));
   public static final Block field_235380_mt_ = register("stripped_crimson_hyphae", new RotatedPillarBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.field_241541_ad_).hardnessAndResistance(2.0F).sound(SoundType.field_235602_z_)));
   public static final Block field_235381_mu_ = register("crimson_nylium", new NyliumBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.field_241539_ab_).func_235861_h_().hardnessAndResistance(0.4F).sound(SoundType.field_235579_A_).tickRandomly()));
   public static final Block field_235382_mv_ = register("crimson_fungus", new FungusBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.NETHERRACK).zeroHardnessAndResistance().doesNotBlockMovement().sound(SoundType.field_235580_B_), () -> {
      return Feature.field_236281_L_.withConfiguration(HugeFungusConfig.field_236299_b_);
   }));
   public static final Block field_235383_mw_ = register("shroomlight", new Block(AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.RED).hardnessAndResistance(1.0F).sound(SoundType.field_235582_D_).func_235838_a_((p_235439_0_) -> {
      return 15;
   })));
   public static final Block field_235384_mx_ = register("weeping_vines", new WeepingVinesTopBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.NETHERRACK).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.field_235583_E_)));
   public static final Block field_235385_my_ = register("weeping_vines_plant", new WeepingVinesBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.NETHERRACK).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.field_235583_E_)));
   public static final Block field_235386_mz_ = register("twisting_vines", new TwistingVinesTopBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.CYAN).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.field_235583_E_)));
   public static final Block field_235342_mA_ = register("twisting_vines_plant", new TwistingVinesBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.CYAN).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.field_235583_E_)));
   public static final Block field_235343_mB_ = register("crimson_roots", new NetherRootsBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS, MaterialColor.NETHERRACK).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.field_235581_C_)));
   public static final Block field_235344_mC_ = register("crimson_planks", new Block(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.field_241540_ac_).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block field_235345_mD_ = register("warped_planks", new Block(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.field_241543_af_).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block field_235346_mE_ = register("crimson_slab", new SlabBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.NETHERRACK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block field_235347_mF_ = register("warped_slab", new SlabBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.CYAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block field_235348_mG_ = register("crimson_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.NETHERRACK).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block field_235349_mH_ = register("warped_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.CYAN).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block field_235350_mI_ = register("crimson_fence", new FenceBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.NETHERRACK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block field_235351_mJ_ = register("warped_fence", new FenceBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.CYAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block field_235352_mK_ = register("crimson_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.NETHERRACK).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
   public static final Block field_235353_mL_ = register("warped_trapdoor", new TrapDoorBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.CYAN).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
   public static final Block field_235354_mM_ = register("crimson_fence_gate", new FenceGateBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.NETHERRACK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block field_235355_mN_ = register("warped_fence_gate", new FenceGateBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.CYAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
   public static final Block field_235356_mO_ = register("crimson_stairs", new StairsBlock(field_235344_mC_.getDefaultState(), AbstractBlock.Properties.from(field_235344_mC_)));
   public static final Block field_235357_mP_ = register("warped_stairs", new StairsBlock(field_235345_mD_.getDefaultState(), AbstractBlock.Properties.from(field_235345_mD_)));
   public static final Block field_235358_mQ_ = register("crimson_button", new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block field_235359_mR_ = register("warped_button", new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
   public static final Block field_235360_mS_ = register("crimson_door", new DoorBlock(AbstractBlock.Properties.create(Material.field_237214_y_, field_235344_mC_.func_235697_s_()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
   public static final Block field_235361_mT_ = register("warped_door", new DoorBlock(AbstractBlock.Properties.create(Material.field_237214_y_, field_235345_mD_.func_235697_s_()).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
   public static final Block field_235362_mU_ = register("crimson_sign", new StandingSignBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.NETHERRACK).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WoodType.field_235923_g_));
   public static final Block field_235363_mV_ = register("warped_sign", new StandingSignBlock(AbstractBlock.Properties.create(Material.field_237214_y_, MaterialColor.CYAN).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WoodType.field_235924_h_));
   public static final Block field_235364_mW_ = register("crimson_wall_sign", new WallSignBlock(AbstractBlock.Properties.create(Material.field_237214_y_).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(field_235362_mU_), WoodType.field_235923_g_));
   public static final Block field_235365_mX_ = register("warped_wall_sign", new WallSignBlock(AbstractBlock.Properties.create(Material.field_237214_y_).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(field_235363_mV_), WoodType.field_235924_h_));
   public static final Block STRUCTURE_BLOCK = register("structure_block", new StructureBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.LIGHT_GRAY).func_235861_h_().hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
   public static final Block JIGSAW = register("jigsaw", new JigsawBlock(AbstractBlock.Properties.create(Material.IRON, MaterialColor.LIGHT_GRAY).func_235861_h_().hardnessAndResistance(-1.0F, 3600000.0F).noDrops()));
   public static final Block COMPOSTER = register("composter", new ComposterBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.6F).sound(SoundType.WOOD)));
   public static final Block field_235396_nb_ = register("target", new TargetBlock(AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.QUARTZ).hardnessAndResistance(0.5F).sound(SoundType.PLANT)));
   public static final Block BEE_NEST = register("bee_nest", new BeehiveBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(0.3F).sound(SoundType.WOOD)));
   public static final Block BEEHIVE = register("beehive", new BeehiveBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.6F).sound(SoundType.WOOD)));
   public static final Block HONEY_BLOCK = register("honey_block", new HoneyBlock(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.ADOBE).speedFactor(0.4F).jumpFactor(0.5F).notSolid().sound(SoundType.field_226947_m_)));
   public static final Block HONEYCOMB_BLOCK = register("honeycomb_block", new Block(AbstractBlock.Properties.create(Material.CLAY, MaterialColor.ADOBE).hardnessAndResistance(0.6F).sound(SoundType.CORAL)));
   public static final Block field_235397_ng_ = register("netherite_block", new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLACK).func_235861_h_().hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.field_235594_P_)));
   public static final Block field_235398_nh_ = register("ancient_debris", new Block(AbstractBlock.Properties.create(Material.IRON, MaterialColor.BLACK).func_235861_h_().hardnessAndResistance(30.0F, 1200.0F).sound(SoundType.field_235595_Q_)));
   public static final Block field_235399_ni_ = register("crying_obsidian", new CryingObsidianBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).func_235861_h_().hardnessAndResistance(50.0F, 1200.0F).func_235838_a_((p_235435_0_) -> {
      return 10;
   })));
   public static final Block field_235400_nj_ = register("respawn_anchor", new RespawnAnchorBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).func_235861_h_().hardnessAndResistance(50.0F, 1200.0F).func_235838_a_((p_235425_0_) -> {
      return RespawnAnchorBlock.func_235565_a_(p_235425_0_, 15);
   })));
   public static final Block field_235401_nk_ = register("potted_crimson_fungus", new FlowerPotBlock(field_235382_mv_, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block field_235402_nl_ = register("potted_warped_fungus", new FlowerPotBlock(field_235373_mm_, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block field_235403_nm_ = register("potted_crimson_roots", new FlowerPotBlock(field_235343_mB_, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block field_235404_nn_ = register("potted_warped_roots", new FlowerPotBlock(field_235375_mo_, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().notSolid()));
   public static final Block field_235405_no_ = register("lodestone", new Block(AbstractBlock.Properties.create(Material.ANVIL).func_235861_h_().hardnessAndResistance(3.5F).sound(SoundType.field_235596_R_)));
   public static final Block field_235406_np_ = register("blackstone", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).func_235861_h_().hardnessAndResistance(1.5F, 6.0F)));
   public static final Block field_235407_nq_ = register("blackstone_stairs", new StairsBlock(field_235406_np_.getDefaultState(), AbstractBlock.Properties.from(field_235406_np_)));
   public static final Block field_235408_nr_ = register("blackstone_wall", new WallBlock(AbstractBlock.Properties.from(field_235406_np_)));
   public static final Block field_235409_ns_ = register("blackstone_slab", new SlabBlock(AbstractBlock.Properties.from(field_235406_np_).hardnessAndResistance(2.0F, 6.0F)));
   public static final Block field_235410_nt_ = register("polished_blackstone", new Block(AbstractBlock.Properties.from(field_235406_np_).hardnessAndResistance(2.0F, 6.0F)));
   public static final Block field_235411_nu_ = register("polished_blackstone_bricks", new Block(AbstractBlock.Properties.from(field_235410_nt_).hardnessAndResistance(1.5F, 6.0F)));
   public static final Block field_235412_nv_ = register("cracked_polished_blackstone_bricks", new Block(AbstractBlock.Properties.from(field_235411_nu_)));
   public static final Block field_235413_nw_ = register("chiseled_polished_blackstone", new Block(AbstractBlock.Properties.from(field_235410_nt_).hardnessAndResistance(1.5F, 6.0F)));
   public static final Block field_235414_nx_ = register("polished_blackstone_brick_slab", new SlabBlock(AbstractBlock.Properties.from(field_235411_nu_).hardnessAndResistance(2.0F, 6.0F)));
   public static final Block field_235415_ny_ = register("polished_blackstone_brick_stairs", new StairsBlock(field_235411_nu_.getDefaultState(), AbstractBlock.Properties.from(field_235411_nu_)));
   public static final Block field_235416_nz_ = register("polished_blackstone_brick_wall", new WallBlock(AbstractBlock.Properties.from(field_235411_nu_)));
   public static final Block field_235387_nA_ = register("gilded_blackstone", new Block(AbstractBlock.Properties.from(field_235406_np_).sound(SoundType.field_235599_U_)));
   public static final Block field_235388_nB_ = register("polished_blackstone_stairs", new StairsBlock(field_235410_nt_.getDefaultState(), AbstractBlock.Properties.from(field_235410_nt_)));
   public static final Block field_235389_nC_ = register("polished_blackstone_slab", new SlabBlock(AbstractBlock.Properties.from(field_235410_nt_)));
   public static final Block field_235390_nD_ = register("polished_blackstone_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).func_235861_h_().doesNotBlockMovement().hardnessAndResistance(0.5F)));
   public static final Block field_235391_nE_ = register("polished_blackstone_button", new StoneButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F)));
   public static final Block field_235392_nF_ = register("polished_blackstone_wall", new WallBlock(AbstractBlock.Properties.from(field_235410_nt_)));
   public static final Block field_235393_nG_ = register("chiseled_nether_bricks", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).func_235861_h_().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.field_235590_L_)));
   public static final Block field_235394_nH_ = register("cracked_nether_bricks", new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).func_235861_h_().hardnessAndResistance(2.0F, 6.0F).sound(SoundType.field_235590_L_)));
   public static final Block field_235395_nI_ = register("quartz_bricks", new Block(AbstractBlock.Properties.from(QUARTZ_BLOCK)));

   private static ToIntFunction<BlockState> func_235420_a_(int p_235420_0_) {
      return (p_235421_1_) -> {
         return p_235421_1_.get(BlockStateProperties.LIT) ? p_235420_0_ : 0;
      };
   }

   private static Boolean func_235427_a_(BlockState p_235427_0_, IBlockReader p_235427_1_, BlockPos p_235427_2_, EntityType<?> p_235427_3_) {
      return (boolean)false;
   }

   private static Boolean func_235437_b_(BlockState p_235437_0_, IBlockReader p_235437_1_, BlockPos p_235437_2_, EntityType<?> p_235437_3_) {
      return (boolean)true;
   }

   private static Boolean func_235441_c_(BlockState p_235441_0_, IBlockReader p_235441_1_, BlockPos p_235441_2_, EntityType<?> p_235441_3_) {
      return p_235441_3_ == EntityType.OCELOT || p_235441_3_ == EntityType.PARROT;
   }

   private static BedBlock func_235422_a_(DyeColor p_235422_0_) {
      return new BedBlock(p_235422_0_, AbstractBlock.Properties.func_235836_a_(Material.WOOL, (p_235424_1_) -> {
         return p_235424_1_.get(BedBlock.PART) == BedPart.FOOT ? p_235422_0_.getMapColor() : MaterialColor.WOOL;
      }).sound(SoundType.WOOD).hardnessAndResistance(0.2F).notSolid());
   }

   private static RotatedPillarBlock func_235430_a_(MaterialColor p_235430_0_, MaterialColor p_235430_1_) {
      return new RotatedPillarBlock(AbstractBlock.Properties.func_235836_a_(Material.WOOD, (p_235431_2_) -> {
         return p_235431_2_.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_235430_0_ : p_235430_1_;
      }).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
   }

   private static Block func_235428_a_(MaterialColor p_235428_0_) {
      return new RotatedPillarBlock(AbstractBlock.Properties.func_235836_a_(Material.field_237214_y_, (p_235429_1_) -> {
         return p_235428_0_;
      }).hardnessAndResistance(2.0F).sound(SoundType.field_235602_z_));
   }

   private static boolean func_235426_a_(BlockState p_235426_0_, IBlockReader p_235426_1_, BlockPos p_235426_2_) {
      return true;
   }

   private static boolean func_235436_b_(BlockState p_235436_0_, IBlockReader p_235436_1_, BlockPos p_235436_2_) {
      return false;
   }

   private static StainedGlassBlock func_235434_b_(DyeColor p_235434_0_) {
      return new StainedGlassBlock(p_235434_0_, AbstractBlock.Properties.create(Material.GLASS, p_235434_0_).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid().func_235827_a_(Blocks::func_235427_a_).func_235828_a_(Blocks::func_235436_b_).func_235842_b_(Blocks::func_235436_b_).func_235847_c_(Blocks::func_235436_b_));
   }

   private static LeavesBlock func_235433_b_() {
      return new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().func_235827_a_(Blocks::func_235441_c_).func_235842_b_(Blocks::func_235436_b_).func_235847_c_(Blocks::func_235436_b_));
   }

   private static ShulkerBoxBlock func_235423_a_(DyeColor p_235423_0_, AbstractBlock.Properties p_235423_1_) {
      AbstractBlock.IPositionPredicate abstractblock$ipositionpredicate = (p_235444_0_, p_235444_1_, p_235444_2_) -> {
         TileEntity tileentity = p_235444_1_.getTileEntity(p_235444_2_);
         if (!(tileentity instanceof ShulkerBoxTileEntity)) {
            return true;
         } else {
            ShulkerBoxTileEntity shulkerboxtileentity = (ShulkerBoxTileEntity)tileentity;
            return shulkerboxtileentity.func_235676_l_();
         }
      };
      return new ShulkerBoxBlock(p_235423_0_, p_235423_1_.hardnessAndResistance(2.0F).variableOpacity().notSolid().func_235842_b_(abstractblock$ipositionpredicate).func_235847_c_(abstractblock$ipositionpredicate));
   }

   private static PistonBlock func_235432_a_(boolean p_235432_0_) {
      AbstractBlock.IPositionPredicate abstractblock$ipositionpredicate = (p_235440_0_, p_235440_1_, p_235440_2_) -> {
         return !p_235440_0_.get(PistonBlock.EXTENDED);
      };
      return new PistonBlock(p_235432_0_, AbstractBlock.Properties.create(Material.PISTON).hardnessAndResistance(1.5F).func_235828_a_(Blocks::func_235436_b_).func_235842_b_(abstractblock$ipositionpredicate).func_235847_c_(abstractblock$ipositionpredicate));
   }

   private static Block register(String key, Block blockIn) {
      return Registry.register(Registry.BLOCK, key, blockIn);
   }

   public static void func_235419_a_() {
      Block.BLOCK_STATE_IDS.forEach(AbstractBlock.AbstractBlockState::cacheState);
   }

   static {
      for(Block block : Registry.BLOCK) {
         for(BlockState blockstate : block.getStateContainer().getValidStates()) {
            Block.BLOCK_STATE_IDS.add(blockstate);
         }

         block.getLootTable();
      }

   }
}