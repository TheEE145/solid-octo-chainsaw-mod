package octo.content;

import mindustry.content.TechTree;
import mindustry.type.ItemStack;

import static octo.core.util.TechTreeUtils.*;

public class OctoTech {
    public static TechTree.TechNode octo;

    public static void load() {
        octo = nodeRoot("@octogen", OctoBlocks.coreOctogen, false, () -> {
            node(OctoStats.brazil, () -> {
                node(OctoItems.octoMat, () -> {
                    node(OctoItems.soul, ItemStack.with(OctoItems.octoMat, 100), () -> {
                        node(OctoBlocks.soulCollector);

                        node(OctoItems.octoMatAlloy, ItemStack.with(
                                OctoItems.octoMat, 200,
                                OctoItems.soul, 100
                        ));
                    });
                });
            });

            node(OctoStats.britany, () -> {
                node(OctoItems.soulLiquid, ItemStack.with(OctoItems.soul, 200), () -> {
                    node(OctoBlocks.soulHardener);
                    node(OctoBlocks.soulSmelter);
                });
            });

            node(OctoStats.imperium, () -> {
                node(OctoItems.magmaCube, ItemStack.with(
                        OctoItems.octoMat, 300,
                        OctoItems.soul, 200,
                        OctoItems.octoMatAlloy, 100
                ));

                node(OctoItems.warlockCube, ItemStack.with(
                        OctoItems.octoMat, 300,
                        OctoItems.soul, 200,
                        OctoItems.octoMatAlloy, 100
                ));
            });

            node(OctoStats.japan, () -> {
                node(OctoItems.substance43, ItemStack.with(
                        OctoItems.soul, 250
                ));
            });

            node(OctoStats.korea, () -> {
                node(OctoItems.slime, ItemStack.with(
                        OctoItems.soul, 250
                ));
            });

            node(OctoStats.pentagon, () -> {
                node(OctoItems.gold, ItemStack.with(
                        OctoItems.steel, 700,
                        OctoItems.silver, 500,
                        OctoItems.soul, 1100,
                        OctoItems.monolith, 300,
                        OctoItems.magmaCube, 100,
                        OctoItems.warlockCube, 100
                ), () -> {
                    node(OctoItems.pentagonium, ItemStack.with(
                            OctoItems.steel, 900,
                            OctoItems.silver, 700,
                            OctoItems.soul, 1300,
                            OctoItems.monolith, 500,
                            OctoItems.magmaCube, 300,
                            OctoItems.warlockCube, 300,
                            OctoItems.gold, 100
                    ), () -> {
                        node(OctoItems.chargedPentagonium, ItemStack.with(
                                OctoItems.monolith,    3000,
                                OctoItems.gold,        3000,
                                OctoItems.pentagonium, 3000
                        ));
                    });
                });
            });

            node(OctoStats.poland, () -> {
                nodeProduce(OctoItems.end, () -> {
                    node(OctoBlocks.earthPower);
                });
            });

            node(OctoStats.sharded, () -> {
                node(OctoItems.steel, ItemStack.with(
                        OctoItems.magmaCube, 100,
                        OctoItems.warlockCube, 100,
                        OctoItems.soul, 300
                ), () -> {
                    node(OctoItems.silver, ItemStack.with(
                            OctoItems.magmaCube, 200,
                            OctoItems.warlockCube, 200,
                            OctoItems.soul, 400,
                            OctoItems.steel, 100
                    ));
                });
            });

            node(OctoStats.usa, () -> {
                node(OctoBlocks.octoChar, () -> {
                    node(OctoBlocks.octoAlpha);

                    node(OctoBlocks.octoTheta, () -> {
                        node(OctoBlocks.octoGamma);

                        node(OctoBlocks.octoDelta, () -> {
                            node(OctoBlocks.octoSigma);

                            node(OctoBlocks.octoFloat, () -> {
                                node(OctoBlocks.octoString);

                                node(OctoBlocks.octoDouble, () -> {
                                    node(OctoBlocks.octoKraken);
                                    node(OctoBlocks.octoTerra);
                                });
                            });
                        });
                    });
                });

                node(OctoBlocks.octoMatWall, () -> {
                    node(OctoBlocks.octoSpike);
                });
            });

            node(OctoStats.wolfenstein, () -> {
                node(OctoItems.monolith, ItemStack.with(
                        OctoItems.magmaCube, 300,
                        OctoItems.warlockCube, 300,
                        OctoItems.soul, 400,
                        OctoItems.steel, 100
                ), () -> {
                    node(OctoBlocks.amplificationTower);

                    node(OctoBlocks.exodus1, () -> {
                        node(OctoBlocks.exodus4, () -> {
                            node(OctoBlocks.exodus8, () -> {
                                node(OctoBlocks.illangeist5, () -> {
                                    node(OctoBlocks.illangeist16, () -> {
                                        node(OctoBlocks.illangeist42, () -> {
                                            node(OctoBlocks.illangeist84, () -> {
                                                node(OctoBlocks.illangeist112);
                                            });

                                            node(OctoBlocks.illangeist42x);
                                        });
                                    });

                                    node(OctoBlocks.illangeist5x);
                                });

                                node(OctoBlocks.exodus24, () -> {
                                    node(OctoBlocks.exodus56, () -> {
                                        node(OctoBlocks.monolith135, () -> {
                                            node(OctoBlocks.monolith157, () -> {
                                                node(OctoBlocks.monolith178, () -> {
                                                    node(OctoBlocks.soulstorm);

                                                    node(OctoBlocks.object70, () -> {
                                                        node(OctoBlocks.DORA);
                                                        node(OctoBlocks.objectMines1);
                                                    });

                                                    node(OctoBlocks.monolith194, () -> {
                                                        node(OctoBlocks.monolith203, () -> {
                                                            node(OctoBlocks.monolith233);
                                                        });
                                                    });
                                                });
                                            });
                                        });

                                        node(OctoBlocks.exodus70, () -> {
                                            node(OctoBlocks.exodus115);
                                        });
                                    });
                                });

                                node(OctoBlocks.exodus8x);
                            });
                        });

                        node(OctoBlocks.exodus1x);
                    });
                });
            });
        });
    }
}