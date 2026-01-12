package com.meowmel.gtme;

import com.meowmel.gtmoreemc.Tags;
import gregtech.api.GregTechAPI;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import moze_intel.projecte.emc.SimpleStack;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static gregtech.common.items.MetaItems.*;
import static moze_intel.projecte.emc.EMCMapper.emc;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class GTMoreEMC {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    /**
     * <a href="https://cleanroommc.com/wiki/forge-mod-development/event#overview">
     * Take a look at how many FMLStateEvents you can listen to via the @Mod.EventHandler annotation here
     * </a>
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("Hello From {}!", Tags.MOD_NAME);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        SimpleStack simpleStack;

        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            int mass = 0;

            if (material.isElement())
                mass = (int) material.getMass();
            else {
                if (material.getMaterialComponents().isEmpty())
                    continue;

                // compute outputs
                for (MaterialStack component : material.getMaterialComponents()) {
                    mass += (int) (component.amount * component.material.getMass());
                }
            }

            long value = mass * 72L;
            if (material.hasProperty(PropertyKey.ORE)) {
                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.ore, material));
                emc.put(simpleStack, value * 4);

                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.oreEndstone, material));
                emc.put(simpleStack, value * 4);

                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.oreNetherrack, material));
                emc.put(simpleStack, value * 4);
            }
            if (material.hasProperty(PropertyKey.DUST)) {
                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.dust, material));
                emc.put(simpleStack, value);

                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.dustSmall, material));
                emc.put(simpleStack, value / 4);

                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.dustTiny, material));
                emc.put(simpleStack, value / 9);
            }
            if (material.hasProperty(PropertyKey.GEM)) {
                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.gem, material));
                emc.put(simpleStack, value);

                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.gemChipped, material));
                emc.put(simpleStack, value / 4);

                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.gemFlawed, material));
                emc.put(simpleStack, value / 2);

                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.gemFlawless, material));
                emc.put(simpleStack, value * 2);

                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.gemExquisite, material));
                emc.put(simpleStack, value * 4);

                if (material.hasFlags(MaterialFlags.GENERATE_LENS)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.lens, material));
                    emc.put(simpleStack, value * 3 / 4);

                }
            }
            if (material.hasProperty(PropertyKey.INGOT)) {
                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.ingot, material));
                emc.put(simpleStack, value);

                if (material.hasProperty(PropertyKey.BLAST)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.ingotHot, material));
                    emc.put(simpleStack, value);
                }

                simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.nugget, material));
                emc.put(simpleStack, value / 9);

                if (material.hasFlags(MaterialFlags.GENERATE_ROD)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.stick, material));
                    emc.put(simpleStack, value / 2);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_LONG_ROD)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.stickLong, material));
                    emc.put(simpleStack, value);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_FOIL)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.foil, material));
                    emc.put(simpleStack, value);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_RING)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.ring, material));
                    emc.put(simpleStack, value / 4);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_SPRING)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.spring, material));
                    emc.put(simpleStack, value);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_SPRING_SMALL)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.springSmall, material));
                    emc.put(simpleStack, value / 4);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_ROUND)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.round, material));
                    emc.put(simpleStack, value);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_BOLT_SCREW)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.bolt, material));
                    emc.put(simpleStack, value / 8);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.screw, material));
                    emc.put(simpleStack, value / 9);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_FINE_WIRE)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.wireFine, material));
                    emc.put(simpleStack, value / 8);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_ROTOR)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.rotor, material));
                    emc.put(simpleStack, value * 4);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_GEAR)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.gear, material));
                    emc.put(simpleStack, value * 4);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_SMALL_GEAR)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.gearSmall, material));
                    emc.put(simpleStack, value);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_FRAME)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.frameGt, material));
                    emc.put(simpleStack, value * 2);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_PLATE)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.plate, material));
                    emc.put(simpleStack, value);
                    if (material.hasFlags(MaterialFlags.GENERATE_DOUBLE_PLATE)) {
                        simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.plateDouble, material));
                        emc.put(simpleStack, value * 2);
                    }
                    if (material.hasFlags(MaterialFlags.GENERATE_DENSE)) {
                        simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.plateDense, material));
                        emc.put(simpleStack, value * 9);
                    }
                }

                if (material.hasProperty(PropertyKey.WIRE)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.wireGtSingle, material));
                    emc.put(simpleStack, value / 2);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.wireGtDouble, material));
                    emc.put(simpleStack, value);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.wireGtQuadruple, material));
                    emc.put(simpleStack, value * 2);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.wireGtOctal, material));
                    emc.put(simpleStack, value * 4);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.wireGtHex, material));
                    emc.put(simpleStack, value * 8);
                }
                if (material.hasProperty(PropertyKey.FLUID_PIPE)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeTinyFluid, material));
                    emc.put(simpleStack, value / 2);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeSmallFluid, material));
                    emc.put(simpleStack, value);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeNormalFluid, material));
                    emc.put(simpleStack, value * 3);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeLargeFluid, material));
                    emc.put(simpleStack, value * 6);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeHugeFluid, material));
                    emc.put(simpleStack, value * 12);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeQuadrupleFluid, material));
                    emc.put(simpleStack, value * 4);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeNonupleFluid, material));
                    emc.put(simpleStack, value * 9);
                }
                if (material.hasProperty(PropertyKey.ITEM_PIPE)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeTinyItem, material));
                    emc.put(simpleStack, value / 2);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeSmallItem, material));
                    emc.put(simpleStack, value);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeNormalItem, material));
                    emc.put(simpleStack, value * 3);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeLargeItem, material));
                    emc.put(simpleStack, value * 6);

                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.pipeHugeItem, material));
                    emc.put(simpleStack, value * 12);
                }

                if (material.hasProperty(PropertyKey.INGOT) || material.hasProperty(PropertyKey.DUST) || material.hasProperty(PropertyKey.GEM)) {
                    simpleStack = new SimpleStack(OreDictUnifier.get(OrePrefix.block, material));
                    emc.put(simpleStack, value * 9);
                }
            }
        }

        //原版
        simpleStack = new SimpleStack(new ItemStack(Items.PAPER));
        emc.put(simpleStack, 32L);

        //物品
        simpleStack = new SimpleStack(WOODEN_FORM_EMPTY.getStackForm());
        emc.put(simpleStack, 8L);

        simpleStack = new SimpleStack(WOODEN_FORM_BRICK.getStackForm());
        emc.put(simpleStack, 8L);

        simpleStack = new SimpleStack(COMPRESSED_COKE_CLAY.getStackForm());
        emc.put(simpleStack, 16L);

        simpleStack = new SimpleStack(COKE_OVEN_BRICK.getStackForm());
        emc.put(simpleStack, 16L);

        simpleStack = new SimpleStack(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.COKE_BRICKS));
        emc.put(simpleStack, 64L);

        simpleStack = new SimpleStack(COMPRESSED_FIRECLAY.getStackForm());
        emc.put(simpleStack, 2106L);

        simpleStack = new SimpleStack(FIRECLAY_BRICK.getStackForm());
        emc.put(simpleStack, 2106L);

        simpleStack = new SimpleStack(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS));
        emc.put(simpleStack, 8424L);

        simpleStack = new SimpleStack(GLASS_TUBE.getStackForm());
        emc.put(simpleStack, 1440L);
    }
}