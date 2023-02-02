package octo.util;

import arc.struct.Seq;
import mindustry.type.Item;
import org.jetbrains.annotations.NotNull;

import mindustry.content.TechTree;
import mindustry.content.TechTree.TechNode;

import mindustry.game.Objectives;
import mindustry.game.Objectives.Objective;

import mindustry.type.ItemStack;
import mindustry.ctype.UnlockableContent;

/**
 * methods from betamindy by sk7725
 * @author sk7725
 */
public class TechTreeUtils {
    static TechNode context = null;

    public static void margeNode(UnlockableContent parent, @NotNull Runnable children){
        context = TechTree.all.find(t -> t.content == parent);
        children.run();
    }

    public static TechNode node(UnlockableContent content,
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

        return node;
    }

    public static TechNode node(UnlockableContent content, ItemStack[] requirements, Runnable children) {
        return node(content, requirements, null, children);
    }

    public static TechNode node(UnlockableContent content, Seq<Objective> objectives, Runnable children) {
        return node(content, content.researchRequirements(), objectives, children);
    }

    public static TechNode node(UnlockableContent content, Seq<Objective> objectives) {
        return node(content, objectives, () -> {});
    }

    public static TechNode node(UnlockableContent content, Runnable children) {
        return node(content, content.researchRequirements(), children);
    }

    public static TechNode node(UnlockableContent content, ItemStack[] requirements) {
        return node(content, requirements, () -> {});
    }

    public static TechNode node(UnlockableContent block) {
        return node(block, () -> {});
    }

    public static TechNode nodeProduce(UnlockableContent content,
                                    @NotNull Seq<Objective> objectives,
                                    Runnable children)
    {
        return node(content, content.researchRequirements(), objectives.add(new Objectives.Produce(content)), children);
    }

    public static TechNode nodeProduce(UnlockableContent content, Runnable children){
        return nodeProduce(content, Seq.with(), children);
    }

    public static TechNode nodeProduce(UnlockableContent content) {
        return nodeProduce(content, Seq.with(), () -> {});
    }

    public static @NotNull TechNode nodeRoot(String name, UnlockableContent content, boolean requireUnlock, Runnable children){
        var root = node(content, content.researchRequirements(), children);
        root.name = name;
        root.requiresUnlock = requireUnlock;
        TechTree.roots.add(root);
        return root;
    }
}