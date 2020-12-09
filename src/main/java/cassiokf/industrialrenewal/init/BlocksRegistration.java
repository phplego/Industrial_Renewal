package cassiokf.industrialrenewal.init;

import cassiokf.industrialrenewal.References;
import cassiokf.industrialrenewal.blocks.*;
import cassiokf.industrialrenewal.blocks.industrialfloor.BlockFloorCable;
import cassiokf.industrialrenewal.blocks.industrialfloor.BlockFloorLamp;
import cassiokf.industrialrenewal.blocks.industrialfloor.BlockFloorPipe;
import cassiokf.industrialrenewal.blocks.industrialfloor.BlockIndustrialFloor;
import cassiokf.industrialrenewal.blocks.pipes.*;
import cassiokf.industrialrenewal.blocks.railroad.*;
import cassiokf.industrialrenewal.blocks.redstone.*;
import cassiokf.industrialrenewal.enums.EnumBulkConveyorType;
import cassiokf.industrialrenewal.enums.EnumEnergyCableType;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static cassiokf.industrialrenewal.References.MODID;

public class BlocksRegistration
{
    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> HAZARD_BLOCK = BLOCKS.register("block_hazard", BlockNormalCube::new);
    public static final RegistryObject<Block> HAZARD_CAUTION = BLOCKS.register("caution_hazard", BlockNormalCube::new);
    public static final RegistryObject<Block> HAZARD_DEFECTIVE = BLOCKS.register("defective_hazard", BlockNormalCube::new);
    public static final RegistryObject<Block> HAZARD_SAFETY = BLOCKS.register("safety_hazard", BlockNormalCube::new);
    public static final RegistryObject<Block> HAZARD_RADIATION = BLOCKS.register("radiation_hazard", BlockNormalCube::new);
    public static final RegistryObject<Block> HAZARD_AISLE = BLOCKS.register("aisle_hazard", BlockNormalCube::new);
    public static final RegistryObject<Block> HAZARD_FIRE = BLOCKS.register("fire_hazard", BlockNormalCube::new);
    public static final RegistryObject<Block> CONCRETE = BLOCKS.register("concrete", BlockConcrete::new);
    public static final RegistryObject<Block> INDFLOOR = BLOCKS.register("industrial_floor", BlockIndustrialFloor::new);
    public static final RegistryObject<Block> CHIMNEY = BLOCKS.register("block_chimney", BlockChimney::new);
    public static final RegistryObject<Block> FIRSTAIDKIT = BLOCKS.register("firstaid_box", BlockFirstAidKit::new);
    public static final RegistryObject<Block> FIREEXTINGUISHER = BLOCKS.register("fire_extinguisher", BlockFireExtinguisher::new);
    public static final RegistryObject<Block> LOCKER = BLOCKS.register("locker", BlockLocker::new);
    public static final RegistryObject<Block> FLUIDPIPE = BLOCKS.register("fluid_pipe", BlockFluidPipe::new);
    public static final RegistryObject<Block> FLUIDPIPEGAUGE = BLOCKS.register("fluid_pipe_gauge", BlockFluidPipeGauge::new);
    public static final RegistryObject<Block> ENERGYCABLELV = BLOCKS.register("energy_cable_lv", () -> new BlockEnergyCable(EnumEnergyCableType.LV));
    public static final RegistryObject<Block> ENERGYCABLEMV = BLOCKS.register("energy_cable_mv", () -> new BlockEnergyCable(EnumEnergyCableType.MV));
    public static final RegistryObject<Block> ENERGYCABLEHV = BLOCKS.register("energy_cable_hv", () -> new BlockEnergyCable(EnumEnergyCableType.HV));
    public static final RegistryObject<Block> ENERGYCABLEGAUGELV = BLOCKS.register("energy_cable_gauge_lv", () -> new BlockEnergyCableGauge(EnumEnergyCableType.LV));
    public static final RegistryObject<Block> ENERGYCABLEGAUGEMV = BLOCKS.register("energy_cable_gauge_mv", () -> new BlockEnergyCableGauge(EnumEnergyCableType.MV));
    public static final RegistryObject<Block> ENERGYCABLEGAUGEHV = BLOCKS.register("energy_cable_gauge_hv", () -> new BlockEnergyCableGauge(EnumEnergyCableType.HV));
    public static final RegistryObject<Block> PILLARENERGYCABLELV = BLOCKS.register("iron_pillar_energy_lv", () -> new BlockPillarEnergyCable(EnumEnergyCableType.LV));
    public static final RegistryObject<Block> PILLARENERGYCABLEMV = BLOCKS.register("iron_pillar_energy_mv", () -> new BlockPillarEnergyCable(EnumEnergyCableType.MV));
    public static final RegistryObject<Block> PILLARENERGYCABLEHV = BLOCKS.register("iron_pillar_energy_hv", () -> new BlockPillarEnergyCable(EnumEnergyCableType.HV));
    public static final RegistryObject<Block> FLOORCABLELV = BLOCKS.register("floor_cable_lv", () -> new BlockFloorCable(EnumEnergyCableType.LV));
    public static final RegistryObject<Block> FLOORCABLEMV = BLOCKS.register("floor_cable_mv", () -> new BlockFloorCable(EnumEnergyCableType.MV));
    public static final RegistryObject<Block> FLOORCABLEHV = BLOCKS.register("floor_cable_hv", () -> new BlockFloorCable(EnumEnergyCableType.HV));
    public static final RegistryObject<Block> CABLETRAY = BLOCKS.register("cable_tray", BlockCableTray::new);
    public static final RegistryObject<Block> PILLARFLUIDPIPE = BLOCKS.register("iron_pillar_pipe", BlockPillarFluidPipe::new);
    public static final RegistryObject<Block> FLOORPIPE = BLOCKS.register("floor_pipe", BlockFloorPipe::new);
    public static final RegistryObject<Block> FLOORLAMP = BLOCKS.register("floor_lamp", BlockFloorLamp::new);
    public static final RegistryObject<Block> HVISOLATOR = BLOCKS.register("isolator_hv", BlockHVIsolator::new);
    public static final RegistryObject<Block> ALARM = BLOCKS.register("alarm", BlockAlarm::new);
    public static final RegistryObject<Block> RECORDPLAYER = BLOCKS.register("record_player", BlockRecordPlayer::new);
    public static final RegistryObject<Block> CATWALK = BLOCKS.register("catwalk", BlockCatWalk::new);
    public static final RegistryObject<Block> CATWALKSTEEL = BLOCKS.register("catwalk_steel", BlockCatWalk::new);
    public static final RegistryObject<Block> HANDRAIL = BLOCKS.register("handrail", BlockHandRail::new);
    public static final RegistryObject<Block> HANDRAILSTEEL = BLOCKS.register("handrail_steel", BlockHandRail::new);
    public static final RegistryObject<Block> CATWALKSTAIR = BLOCKS.register("catwalk_stair", BlockCatwalkStair::new);
    public static final RegistryObject<Block> CATWALKSTAIRSTEEL = BLOCKS.register("catwalk_stair_steel", BlockCatwalkStair::new);
    public static final RegistryObject<Block> PILLAR = BLOCKS.register("catwalk_pillar", BlockPillar::new);
    public static final RegistryObject<Block> STEEL_PILLAR = BLOCKS.register("catwalk_steel_pillar", BlockPillar::new);
    public static final RegistryObject<Block> COLUMN = BLOCKS.register("catwalk_column", BlockColumn::new);
    public static final RegistryObject<Block> COLUMSTEEL = BLOCKS.register("catwalk_column_steel", BlockColumn::new);
    public static final RegistryObject<Block> ILADDER = BLOCKS.register("catwalk_ladder", BlockCatwalkLadder::new);
    public static final RegistryObject<Block> SLADDER = BLOCKS.register("catwalk_ladder_steel", BlockCatwalkLadder::new);
    public static final RegistryObject<Block> ROOF = BLOCKS.register("roof", BlockRoof::new);
    public static final RegistryObject<Block> GUTTER = BLOCKS.register("gutter", BlockGutter::new);
    public static final RegistryObject<Block> LIGHT = BLOCKS.register("light", BlockLight::new);
    public static final RegistryObject<Block> FLUORESCENT = BLOCKS.register("fluorescent", BlockFluorescent::new);
    //public static final RegistryObject<Block> DUMMY = BLOCKS.register("dummy", BlockDummy::new);
    public static final RegistryObject<Block> CATWALKGATE = BLOCKS.register("catwalk_gate", BlockCatwalkGate::new);
    public static final RegistryObject<Block> HATCH = BLOCKS.register("catwalk_hatch", BlockCatwalkHatch::new);
    public static final RegistryObject<Block> WINDOW = BLOCKS.register("window", BlockWindow::new);
    public static final RegistryObject<Block> PLATFORM = BLOCKS.register("platform", BlockPlatform::new);
    public static final RegistryObject<Block> BRACE = BLOCKS.register("brace", BlockBrace::new);
    public static final RegistryObject<Block> BRACESTEEL = BLOCKS.register("brace_steel", BlockBrace::new);
    public static final RegistryObject<Block> SCAFFOLD = BLOCKS.register("scaffold", BlockScaffold::new);
    public static final RegistryObject<Block> FRAME = BLOCKS.register("frame", BlockFrame::new);
    public static final RegistryObject<Block> BUNKBED = BLOCKS.register("bunkbed", BlockBunkBed::new);
    public static final RegistryObject<Block> BUNKERHATCH = BLOCKS.register("bunker_hatch", BlockBunkerHatch::new);
    public static final RegistryObject<Block> BARREL = BLOCKS.register("barrel", BlockBarrel::new);
    public static final RegistryObject<Block> TRASH = BLOCKS.register("trash", BlockTrash::new);
    public static final RegistryObject<Block> GAUGE = BLOCKS.register("fluid_gauge", BlockGauge::new);
    public static final RegistryObject<Block> ENERGYLEVEL = BLOCKS.register("energy_level", BlockEnergyLevel::new);
    public static final RegistryObject<Block> EFENCE = BLOCKS.register("electric_fence", BlockElectricFence::new);
    public static final RegistryObject<Block> BIGFENCECOLUMN = BLOCKS.register("fence_big_column", BlockElectricBigFenceColumn::new);
    public static final RegistryObject<Block> BIGFENCEWIRE = BLOCKS.register("fence_big_wire", BlockBigFenceWire::new);
    public static final RegistryObject<Block> BIGFENCECORNER = BLOCKS.register("fence_big_corner", BlockElectricBigFenceCorner::new);
    public static final RegistryObject<Block> CONCRETEWALL = BLOCKS.register("wall_concrete", BlockBaseWall::new);
    public static final RegistryObject<Block> EGATE = BLOCKS.register("electric_gate", BlockElectricGate::new);
    public static final RegistryObject<Block> RAZORWIRE = BLOCKS.register("razor_wire", BlockRazorWire::new);
    public static final RegistryObject<Block> DAMINTAKE = BLOCKS.register("dam_intake", BlockDamIntake::new);
    public static final RegistryObject<Block> INFINITYGENERATOR = BLOCKS.register("infinity_generator", BlockInfinityGenerator::new);
    public static final RegistryObject<Block> SPANEL = BLOCKS.register("solar_panel", BlockSolarPanel::new);
    public static final RegistryObject<Block> FPANEL = BLOCKS.register("solar_panel_frame", BlockSolarPanelFrame::new);
    public static final RegistryObject<Block> SWINDTURBINE = BLOCKS.register("small_wind_turbine", BlockWindTurbineSmall::new);
    public static final RegistryObject<Block> TURBINEPILLAR = BLOCKS.register("small_wind_turbine_pillar", BlockWindTurbinePillar::new);
    public static final RegistryObject<Block> ELECTRICPUMP = BLOCKS.register("electric_pump", BlockElectricPump::new);
    public static final RegistryObject<Block> BATTERYBANK = BLOCKS.register("battery_bank", BlockBatteryBank::new);
    public static final RegistryObject<Block> SENSORRAIN = BLOCKS.register("sensor_rain", BlockSensorRain::new);
    public static final RegistryObject<Block> SIGNALINDICATOR = BLOCKS.register("signal_indicator", BlockSignalIndicator::new);
    public static final RegistryObject<Block> TRAFFICLIGHT = BLOCKS.register("traffic_light", BlockTrafficLight::new);
    public static final RegistryObject<Block> FUSEBOX = BLOCKS.register("fuse_box", BlockFuseBox::new);
    public static final RegistryObject<Block> FUSEBOXCONDUITEXTENSION = BLOCKS.register("conduit_extension", BlockFuseBoxConduitExtension::new);
    public static final RegistryObject<Block> FUSEBOXCONNECTOR = BLOCKS.register("conduit_connector", BlockFuseBoxConnector::new);
    public static final RegistryObject<Block> FLAMEDETECTOR = BLOCKS.register("flame_detector", BlockFlameDetector::new);
    public static final RegistryObject<Block> ENTITYDETECTOR = BLOCKS.register("entity_detector", BlockEntityDetector::new);
    public static final RegistryObject<Block> BUTTONRED = BLOCKS.register("button_red", BlockButtonRed::new);
    public static final RegistryObject<Block> NORMALRAIL = BLOCKS.register("normal_rail", BlockNormalRail::new);
    public static final RegistryObject<Block> CROSSINGRAIL = BLOCKS.register("crossing_rail", BlockCrossingRail::new);
    public static final RegistryObject<Block> DETECTORRAIL = BLOCKS.register("detector_rail", BlockDetectorRail::new);
    public static final RegistryObject<Block> BOOSTERRAIL = BLOCKS.register("booster_rail", BlockBoosterRail::new);
    public static final RegistryObject<Block> BUFFERSTOPRAIL = BLOCKS.register("buffer_stop_rail", BlockBufferStopRail::new);
    public static final RegistryObject<Block> LOADERRAIL = BLOCKS.register("rail_loader", BlockLoaderRail::new);
    public static final RegistryObject<Block> RAILGATE = BLOCKS.register("rail_gate", BlockRailGate::new);
    public static final RegistryObject<Block> CARGOLOADER = BLOCKS.register("cargo_loader", BlockCargoLoader::new);
    public static final RegistryObject<Block> FLUIDLOADER = BLOCKS.register("fluid_loader", BlockFluidLoader::new);
    public static final RegistryObject<Block> VALVELARGE = BLOCKS.register("valve_pipe_large", BlockValvePipeLarge::new);
    public static final RegistryObject<Block> ENERGYSWITCH = BLOCKS.register("energy_switch", BlockEnergySwitch::new);
    public static final RegistryObject<Block> CONVEYORV = BLOCKS.register("conveyor_bulk", () -> new BlockBulkConveyor(EnumBulkConveyorType.NORMAL));
    public static final RegistryObject<Block> CONVEYORVHOPPER = BLOCKS.register("conveyor_bulk_hopper", () -> new BlockBulkConveyor(EnumBulkConveyorType.HOPPER));
    public static final RegistryObject<Block> CONVEYORVINSERTER = BLOCKS.register("conveyor_bulk_inserter", () -> new BlockBulkConveyor(EnumBulkConveyorType.INSERTER));
    public static final RegistryObject<Block> SIGNHV = BLOCKS.register("sign_hv", BlockSignBase::new);
    public static final RegistryObject<Block> SIGNRA = BLOCKS.register("sign_ra", BlockSignBase::new);
    public static final RegistryObject<Block> SIGNC = BLOCKS.register("sign_c", BlockSignBase::new);
    public static final RegistryObject<Block> BASEEOTM = BLOCKS.register("eotm", BlockEotM::new);
    public static final RegistryObject<Block> STEAMBOILER = BLOCKS.register("steam_boiler", BlockSteamBoiler::new);
    public static final RegistryObject<Block> STEAMTURBINE = BLOCKS.register("steam_turbine", BlockSteamTurbine::new);
    public static final RegistryObject<Block> MINING = BLOCKS.register("mining", BlockMining::new);
    public static final RegistryObject<Block> TRANSFORMERHV = BLOCKS.register("transformer_hv", BlockTransformerHV::new);
    public static final RegistryObject<Block> VEINHEMATITE = BLOCKS.register("orevein_hematite", BlockOreVein::new);


    private static final DeferredRegister<Item> BLOCK_ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MODID);

    //public static final RegistryObject<Block> BLOCK = BLOCKS.register("chunk_loader", BlockChunkLoader::new);
    //===================
    public static final RegistryObject<Item> HAZARD_BLOCK_ITEM = BLOCK_ITEMS.register("block_hazard", () -> new BlockItem(HAZARD_BLOCK.get(), new Item.Properties().group(References.GROUP_INDR)));

    //public static final RegistryObject<Block> STEAMBLOCK = BLOCKS.register("steam", BlockSteam::new);
    public static final RegistryObject<Item> HAZARD_CAUTION_ITEM = BLOCK_ITEMS.register("caution_hazard", () -> new BlockItem(HAZARD_CAUTION.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> HAZARD_DEFECTIVE_ITEM = BLOCK_ITEMS.register("defective_hazard", () -> new BlockItem(HAZARD_DEFECTIVE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> HAZARD_SAFETY_ITEM = BLOCK_ITEMS.register("safety_hazard", () -> new BlockItem(HAZARD_SAFETY.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> HAZARD_RADIATION_ITEM = BLOCK_ITEMS.register("radiation_hazard", () -> new BlockItem(HAZARD_RADIATION.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> HAZARD_AISLE_ITEM = BLOCK_ITEMS.register("aisle_hazard", () -> new BlockItem(HAZARD_AISLE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> HAZARD_FIRE_ITEM = BLOCK_ITEMS.register("fire_hazard", () -> new BlockItem(HAZARD_FIRE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CONCRETE_ITEM = BLOCK_ITEMS.register("concrete", () -> new BlockItem(CONCRETE.get(), new Item.Properties().group(References.GROUP_INDR_WIP)));
    public static final RegistryObject<Item> INDFLOOR_ITEM = BLOCK_ITEMS.register("industrial_floor", () -> new BlockItem(INDFLOOR.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CHIMNEY_ITEM = BLOCK_ITEMS.register("block_chimney", () -> new BlockItem(CHIMNEY.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> FIRSTAIDKIT_ITEM = BLOCK_ITEMS.register("firstaid_box", () -> new BlockItem(FIRSTAIDKIT.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> LOCKER_ITEM = BLOCK_ITEMS.register("locker", () -> new BlockItem(LOCKER.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> FLUIDPIPE_ITEM = BLOCK_ITEMS.register("fluid_pipe", () -> new BlockItem(FLUIDPIPE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> ENERGYCABLELV_ITEM = BLOCK_ITEMS.register("energy_cable_lv", () -> new BlockItem(ENERGYCABLELV.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> ENERGYCABLEMV_ITEM = BLOCK_ITEMS.register("energy_cable_mv", () -> new BlockItem(ENERGYCABLEMV.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> ENERGYCABLEHV_ITEM = BLOCK_ITEMS.register("energy_cable_hv", () -> new BlockItem(ENERGYCABLEHV.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CABLETRAY_ITEM = BLOCK_ITEMS.register("cable_tray", () -> new BlockItem(CABLETRAY.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> HVISOLATOR_ITEM = BLOCK_ITEMS.register("isolator_hv", () -> new BlockItem(HVISOLATOR.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> ALARM_ITEM = BLOCK_ITEMS.register("alarm", () -> new BlockItem(ALARM.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> RECORDPLAYER_ITEM = BLOCK_ITEMS.register("record_player", () -> new BlockItem(RECORDPLAYER.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CATWALK_ITEM = BLOCK_ITEMS.register("catwalk", () -> new BlockItem(CATWALK.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CATWALKSTEEL_ITEM = BLOCK_ITEMS.register("catwalk_steel", () -> new BlockItem(CATWALKSTEEL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> HANDRAIL_ITEM = BLOCK_ITEMS.register("handrail", () -> new BlockItem(HANDRAIL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> HANDRAILSTEEL_ITEM = BLOCK_ITEMS.register("handrail_steel", () -> new BlockItem(HANDRAILSTEEL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CATWALKSTAIR_ITEM = BLOCK_ITEMS.register("catwalk_stair", () -> new BlockItem(CATWALKSTAIR.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CATWALKSTAIRSTEEL_ITEM = BLOCK_ITEMS.register("catwalk_stair_steel", () -> new BlockItem(CATWALKSTAIRSTEEL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> PILLAR_ITEM = BLOCK_ITEMS.register("catwalk_pillar", () -> new BlockItem(PILLAR.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> STEEL_PILLAR_ITEM = BLOCK_ITEMS.register("catwalk_steel_pillar", () -> new BlockItem(STEEL_PILLAR.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> COLUMN_ITEM = BLOCK_ITEMS.register("catwalk_column", () -> new BlockItem(COLUMN.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> COLUMSTEEL_ITEM = BLOCK_ITEMS.register("catwalk_column_steel", () -> new BlockItem(COLUMSTEEL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> ILADDER_ITEM = BLOCK_ITEMS.register("catwalk_ladder", () -> new BlockItem(ILADDER.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> SLADDER_ITEM = BLOCK_ITEMS.register("catwalk_ladder_steel", () -> new BlockItem(SLADDER.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> ROOF_ITEM = BLOCK_ITEMS.register("roof", () -> new BlockItem(ROOF.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> GUTTER_ITEM = BLOCK_ITEMS.register("gutter", () -> new BlockItem(GUTTER.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> LIGHT_ITEM = BLOCK_ITEMS.register("light", () -> new BlockItem(LIGHT.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> FLUORESCENT_ITEM = BLOCK_ITEMS.register("fluorescent", () -> new BlockItem(FLUORESCENT.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CATWALKGATE_ITEM = BLOCK_ITEMS.register("catwalk_gate", () -> new BlockItem(CATWALKGATE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> HATCH_ITEM = BLOCK_ITEMS.register("catwalk_hatch", () -> new BlockItem(HATCH.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> WINDOW_ITEM = BLOCK_ITEMS.register("window", () -> new BlockItem(WINDOW.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> PLATFORM_ITEM = BLOCK_ITEMS.register("platform", () -> new BlockItem(PLATFORM.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BRACE_ITEM = BLOCK_ITEMS.register("brace", () -> new BlockItem(BRACE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BRACESTEEL_ITEM = BLOCK_ITEMS.register("brace_steel", () -> new BlockItem(BRACESTEEL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> SCAFFOLD_ITEM = BLOCK_ITEMS.register("scaffold", () -> new BlockItem(SCAFFOLD.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> FRAME_ITEM = BLOCK_ITEMS.register("frame", () -> new BlockItem(FRAME.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BUNKBED_ITEM = BLOCK_ITEMS.register("bunkbed", () -> new BlockItem(BUNKBED.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BUNKERHATCH_ITEM = BLOCK_ITEMS.register("bunker_hatch", () -> new BlockItem(BUNKERHATCH.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> TRASH_ITEM = BLOCK_ITEMS.register("trash", () -> new BlockItem(TRASH.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> GAUGE_ITEM = BLOCK_ITEMS.register("fluid_gauge", () -> new BlockItem(GAUGE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> ENERGYLEVEL_ITEM = BLOCK_ITEMS.register("energy_level", () -> new BlockItem(ENERGYLEVEL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> EFENCE_ITEM = BLOCK_ITEMS.register("electric_fence", () -> new BlockItem(EFENCE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BIGFENCECOLUMN_ITEM = BLOCK_ITEMS.register("fence_big_column", () -> new BlockItem(BIGFENCECOLUMN.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BIGFENCEWIRE_ITEM = BLOCK_ITEMS.register("fence_big_wire", () -> new BlockItem(BIGFENCEWIRE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BIGFENCECORNER_ITEM = BLOCK_ITEMS.register("fence_big_corner", () -> new BlockItem(BIGFENCECORNER.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CONCRETEWALL_ITEM = BLOCK_ITEMS.register("wall_concrete", () -> new BlockItem(CONCRETEWALL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> EGATE_ITEM = BLOCK_ITEMS.register("electric_gate", () -> new BlockItem(EGATE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> RAZORWIRE_ITEM = BLOCK_ITEMS.register("razor_wire", () -> new BlockItem(RAZORWIRE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> DAMINTAKE_ITEM = BLOCK_ITEMS.register("dam_intake", () -> new BlockItem(DAMINTAKE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> INFINITYGENERATOR_ITEM = BLOCK_ITEMS.register("infinity_generator", () -> new BlockItem(INFINITYGENERATOR.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> SPANEL_ITEM = BLOCK_ITEMS.register("solar_panel", () -> new BlockItem(SPANEL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> FPANEL_ITEM = BLOCK_ITEMS.register("solar_panel_frame", () -> new BlockItem(FPANEL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> SWINDTURBINE_ITEM = BLOCK_ITEMS.register("small_wind_turbine", () -> new BlockItem(SWINDTURBINE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> TURBINEPILLAR_ITEM = BLOCK_ITEMS.register("small_wind_turbine_pillar", () -> new BlockItem(TURBINEPILLAR.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> ELECTRICPUMP_ITEM = BLOCK_ITEMS.register("electric_pump", () -> new BlockItem(ELECTRICPUMP.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BATTERYBANK_ITEM = BLOCK_ITEMS.register("battery_bank", () -> new BlockItem(BATTERYBANK.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> SENSORRAIN_ITEM = BLOCK_ITEMS.register("sensor_rain", () -> new BlockItem(SENSORRAIN.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> SIGNALINDICATOR_ITEM = BLOCK_ITEMS.register("signal_indicator", () -> new BlockItem(SIGNALINDICATOR.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> TRAFFICLIGHT_ITEM = BLOCK_ITEMS.register("traffic_light", () -> new BlockItem(TRAFFICLIGHT.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> FUSEBOX_ITEM = BLOCK_ITEMS.register("fuse_box", () -> new BlockItem(FUSEBOX.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> FUSEBOXCONDUITEXTENSION_ITEM = BLOCK_ITEMS.register("conduit_extension", () -> new BlockItem(FUSEBOXCONDUITEXTENSION.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> FUSEBOXCONNECTOR_ITEM = BLOCK_ITEMS.register("conduit_connector", () -> new BlockItem(FUSEBOXCONNECTOR.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> FLAMEDETECTOR_ITEM = BLOCK_ITEMS.register("flame_detector", () -> new BlockItem(FLAMEDETECTOR.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> ENTITYDETECTOR_ITEM = BLOCK_ITEMS.register("entity_detector", () -> new BlockItem(ENTITYDETECTOR.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BUTTONRED_ITEM = BLOCK_ITEMS.register("button_red", () -> new BlockItem(BUTTONRED.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> NORMALRAIL_ITEM = BLOCK_ITEMS.register("normal_rail", () -> new BlockItem(NORMALRAIL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CROSSINGRAIL_ITEM = BLOCK_ITEMS.register("crossing_rail", () -> new BlockItem(CROSSINGRAIL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> DETECTORRAIL_ITEM = BLOCK_ITEMS.register("detector_rail", () -> new BlockItem(DETECTORRAIL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BOOSTERRAIL_ITEM = BLOCK_ITEMS.register("booster_rail", () -> new BlockItem(BOOSTERRAIL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BUFFERSTOPRAIL_ITEM = BLOCK_ITEMS.register("buffer_stop_rail", () -> new BlockItem(BUFFERSTOPRAIL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> LOADERRAIL_ITEM = BLOCK_ITEMS.register("rail_loader", () -> new BlockItem(LOADERRAIL.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> RAILGATE_ITEM = BLOCK_ITEMS.register("rail_gate", () -> new BlockItem(RAILGATE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CARGOLOADER_ITEM = BLOCK_ITEMS.register("cargo_loader", () -> new BlockItem(CARGOLOADER.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> FLUIDLOADER_ITEM = BLOCK_ITEMS.register("fluid_loader", () -> new BlockItem(FLUIDLOADER.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> VALVELARGE_ITEM = BLOCK_ITEMS.register("valve_pipe_large", () -> new BlockItem(VALVELARGE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> ENERGYSWITCH_ITEM = BLOCK_ITEMS.register("energy_switch", () -> new BlockItem(ENERGYSWITCH.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> CONVEYORV_ITEM = BLOCK_ITEMS.register("conveyor_bulk", () -> new BlockItem(CONVEYORV.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> SIGNHV_ITEM = BLOCK_ITEMS.register("sign_hv", () -> new BlockItem(SIGNHV.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> BASEEOTM_ITEM = BLOCK_ITEMS.register("eotm", () -> new BlockItem(BASEEOTM.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> STEAMBOILER_ITEM = BLOCK_ITEMS.register("steam_boiler", () -> new BlockItem(STEAMBOILER.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> STEAMTURBINE_ITEM = BLOCK_ITEMS.register("steam_turbine", () -> new BlockItem(STEAMTURBINE.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> MINING_ITEM = BLOCK_ITEMS.register("mining", () -> new BlockItem(MINING.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> TRANSFORMERHV_ITEM = BLOCK_ITEMS.register("transformer_hv", () -> new BlockItem(TRANSFORMERHV.get(), new Item.Properties().group(References.GROUP_INDR)));
    public static final RegistryObject<Item> VEINHEMATITE_ITEM = BLOCK_ITEMS.register("orevein_hematite", () -> new BlockItem(VEINHEMATITE.get(), new Item.Properties().group(References.GROUP_INDR)));

    //public static final RegistryObject<Item> BLOCK_ITEM = BLOCK_ITEMS.register("chunk_loader", () -> new BlockItem(BLOCK.get(), new Item.Properties().group(References.GROUP_INDR)));

    public static void init()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //public static final RegistryObject<Item> STEAMBLOCK_ITEM = BLOCK_ITEMS.register("steam", () -> new BlockItem(STEAMBLOCK.get(), new Item.Properties().group(References.GROUP_INDR)));

}
