package octo.util;

import arc.struct.Seq;
import mindustry.content.TechTree;
import mindustry.content.TechTree.TechNode;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.game.Objectives.Objective;
import mindustry.type.ItemStack;
import org.jetbrains.annotations.NotNull;

/* methods from betamindy by sk7725 */
public class TechTreeUtils {
    static TechNode context = null;

    public static void margeNode(UnlockableContent parent, @NotNull Runnable children){
        context = TechTree.all.find(t -> t.content == parent);
        children.run();
    }

    public static void node(UnlockableContent content,
                            ItemStack[] requirements,
                            Seq<Objective> objectives,
                            Runnable children)
    {
        TechNode node = new TechNode(context, content, requirements);
        if(objectives != null) node.objectives = objectives;

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    public static void node(UnlockableContent content, ItemStack[] requirements, Runnable children) {
        node(content, requirements, null, children);
    }

    public static void node(UnlockableContent content, Seq<Objective> objectives, Runnable children) {
        node(content, content.researchRequirements(), objectives, children);
    }

    public static void node(UnlockableContent content, Runnable children) {
        node(content, content.researchRequirements(), children);
    }

    public static void node(UnlockableContent block) {
        node(block, () -> {});
    }

    public static void nodeProduce(UnlockableContent content,
                                    @NotNull Seq<Objective> objectives,
                                    Runnable children)
    {
        node(content, content.researchRequirements(), objectives.add(new Objectives.Produce(content)), children);
    }

    public static void nodeProduce(UnlockableContent content, Runnable children){
        nodeProduce(content, Seq.with(), children);
    }

    public static void nodeProduce(UnlockableContent content){
        nodeProduce(content, Seq.with(), () -> {});
    }
}