package octo.content;

import mindustry.type.ItemStack;
import static mindustry.content.TechTree.*;

public class OctoTech {
    public static TechNode octo;

    public static void load() {
        octo = nodeRoot("@octogen", OctoBlocks.coreOctogen, false, () -> {
            node(OctoStats.brazil, () -> {
                node(OctoItems.octoMat, () -> {
                    node(OctoItems.soul, ItemStack.with(OctoItems.octoMat, 100), () -> {
                        node(OctoBlocks.soulCollector);
                    });
                });
            });

            node(OctoStats.britany, () -> {
                node(OctoItems.soulLiquid, ItemStack.with(OctoItems.soul, 200), () -> {
                    node(OctoBlocks.soulHardener);
                    node(OctoBlocks.soulSmelter);
                });
            });

            node(OctoStats.imperium);

            node(OctoStats.japan);

            node(OctoStats.korea);

            node(OctoStats.pentagon);

            node(OctoStats.poland, () -> {
                node(OctoBlocks.earthPower);
            });

            node(OctoStats.sharded);

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
                nodeProduce(OctoItems.monolith, () -> {
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