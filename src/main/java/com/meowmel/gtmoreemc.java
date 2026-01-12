package com.meowmel;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import moze_intel.projecte.api.imc.CustomEMCRegistration;
import moze_intel.projecte.api.nss.NSSItem;
import moze_intel.projecte.emc.mappers.APICustomEMCMapper;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(gtmoreemc.MODID)
public class gtmoreemc {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "gtmoreemc";

    public gtmoreemc() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::loadComplete);

        // Most other events are fired on Forge's bus.
        // If we want to use annotations to register event listeners,
        // we need to register our object like this!
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void loadComplete(FMLLoadCompleteEvent e) {
        System.out.println("gtmoreemc loaded");
        ItemStack simpleStack;
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            int mass = 0;

            if (material.isElement())
                mass = (int) material.getMass();
            else {
                if (material.getMaterialComponents().isEmpty())
                    continue;

                // compute outputs
                for (MaterialStack component : material.getMaterialComponents()) {
                    mass += (int) (component.amount() * component.material().getMass());
                }
            }

            long value = mass * 72L;
            if (material.hasProperty(PropertyKey.ORE)) {
                simpleStack = ChemicalHelper.get(TagPrefix.ore, material);
                put(simpleStack, value * 4);

                simpleStack = ChemicalHelper.get(TagPrefix.oreEndstone, material);
                put(simpleStack, value * 4);

                simpleStack = ChemicalHelper.get(TagPrefix.oreNetherrack, material);
                put(simpleStack, value * 4);
            }
            if (material.hasProperty(PropertyKey.DUST)) {
                simpleStack = ChemicalHelper.get(TagPrefix.dust, material);
                put(simpleStack, value);

                simpleStack = ChemicalHelper.get(TagPrefix.dustSmall, material);
                put(simpleStack, value / 4);

                simpleStack = ChemicalHelper.get(TagPrefix.dustTiny, material);
                put(simpleStack, value / 9);
            }
            if (material.hasProperty(PropertyKey.GEM)) {
                simpleStack = ChemicalHelper.get(TagPrefix.gem, material);
                put(simpleStack, value);

                simpleStack = ChemicalHelper.get(TagPrefix.gemChipped, material);
                put(simpleStack, value / 4);

                simpleStack = ChemicalHelper.get(TagPrefix.gemFlawed, material);
                put(simpleStack, value / 2);

                simpleStack = ChemicalHelper.get(TagPrefix.gemFlawless, material);
                put(simpleStack, value * 2);

                simpleStack = ChemicalHelper.get(TagPrefix.gemExquisite, material);
                put(simpleStack, value * 4);

                if (material.hasFlags(MaterialFlags.GENERATE_LENS)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.lens, material);
                    put(simpleStack, value * 3 / 4);

                }
            }
            if (material.hasProperty(PropertyKey.INGOT)) {
                simpleStack = ChemicalHelper.get(TagPrefix.ingot, material);
                put(simpleStack, value);

                if (material.hasProperty(PropertyKey.BLAST)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.ingotHot, material);
                    put(simpleStack, value);
                }

                simpleStack = ChemicalHelper.get(TagPrefix.nugget, material);
                put(simpleStack, value / 9);

                if (material.hasFlags(MaterialFlags.GENERATE_ROD)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.rod, material);
                    put(simpleStack, value / 2);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_LONG_ROD)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.rodLong, material);
                    put(simpleStack, value);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_FOIL)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.foil, material);
                    put(simpleStack, value);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_RING)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.ring, material);
                    put(simpleStack, value / 4);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_SPRING)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.spring, material);
                    put(simpleStack, value);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_SPRING_SMALL)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.springSmall, material);
                    put(simpleStack, value / 4);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_ROUND)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.round, material);
                    put(simpleStack, value);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_BOLT_SCREW)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.bolt, material);
                    put(simpleStack, value / 8);

                    simpleStack = ChemicalHelper.get(TagPrefix.screw, material);
                    put(simpleStack, value / 9);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_FINE_WIRE)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.wireFine, material);
                    put(simpleStack, value / 8);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_ROTOR)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.rotor, material);
                    put(simpleStack, value * 4);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_GEAR)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.gear, material);
                    put(simpleStack, value * 4);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_SMALL_GEAR)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.gearSmall, material);
                    put(simpleStack, value);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_FRAME)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.frameGt, material);
                    put(simpleStack, value * 2);
                }
                if (material.hasFlags(MaterialFlags.GENERATE_PLATE)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.plate, material);
                    put(simpleStack, value);

                    simpleStack = ChemicalHelper.get(TagPrefix.plateDouble, material);
                    put(simpleStack, value * 2);

                    if (material.hasFlags(MaterialFlags.GENERATE_DENSE)) {
                        simpleStack = ChemicalHelper.get(TagPrefix.plateDense, material);
                        put(simpleStack, value * 9);
                    }
                }


                if (material.hasProperty(PropertyKey.WIRE)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.wireGtSingle, material);
                    put(simpleStack, value / 2);

                    simpleStack = ChemicalHelper.get(TagPrefix.wireGtDouble, material);
                    put(simpleStack, value);

                    simpleStack = ChemicalHelper.get(TagPrefix.wireGtQuadruple, material);
                    put(simpleStack, value * 2);

                    simpleStack = ChemicalHelper.get(TagPrefix.wireGtOctal, material);
                    put(simpleStack, value * 4);

                    simpleStack = ChemicalHelper.get(TagPrefix.wireGtHex, material);
                    put(simpleStack, value * 8);
                }
                if (material.hasProperty(PropertyKey.FLUID_PIPE)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.pipeTinyFluid, material);
                    put(simpleStack, value / 2);

                    simpleStack = ChemicalHelper.get(TagPrefix.pipeSmallFluid, material);
                    put(simpleStack, value);

                    simpleStack = ChemicalHelper.get(TagPrefix.pipeNormalFluid, material);
                    put(simpleStack, value * 3);

                    simpleStack = ChemicalHelper.get(TagPrefix.pipeLargeFluid, material);
                    put(simpleStack, value * 6);

                    simpleStack = ChemicalHelper.get(TagPrefix.pipeHugeFluid, material);
                    put(simpleStack, value * 12);

                    simpleStack = ChemicalHelper.get(TagPrefix.pipeQuadrupleFluid, material);
                    put(simpleStack, value * 4);

                    simpleStack = ChemicalHelper.get(TagPrefix.pipeNonupleFluid, material);
                    put(simpleStack, value * 9);
                }
                if (material.hasProperty(PropertyKey.ITEM_PIPE)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.pipeSmallItem, material);
                    put(simpleStack, value);

                    simpleStack = ChemicalHelper.get(TagPrefix.pipeNormalItem, material);
                    put(simpleStack, value * 3);

                    simpleStack = ChemicalHelper.get(TagPrefix.pipeLargeItem, material);
                    put(simpleStack, value * 6);

                    simpleStack = ChemicalHelper.get(TagPrefix.pipeHugeItem, material);
                    put(simpleStack, value * 12);
                }

                if (material.hasProperty(PropertyKey.INGOT) || material.hasProperty(PropertyKey.DUST) || material.hasProperty(PropertyKey.GEM)) {
                    simpleStack = ChemicalHelper.get(TagPrefix.block, material);
                    put(simpleStack, value * 9);
                }
            }
        }


    }

    public void put(ItemStack stack, long value) {
        if (stack != null && stack != ItemStack.EMPTY)
            APICustomEMCMapper.INSTANCE.registerCustomEMC(gtmoreemc.MODID, new CustomEMCRegistration(NSSItem.createItem(stack), value));
    }
}



