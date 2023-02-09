package octo.world;

import arc.scene.ui.layout.Table;
import octo.core.util.IllegalItemSelection;
import mindustry.world.blocks.sandbox.ItemSource;

import static mindustry.Vars.content;

public class IllegalItemSource extends ItemSource {
    public IllegalItemSource(String name) {
        super(name);
    }

    public class IllegalItemSourceBuild extends ItemSourceBuild {
        @Override
        public void buildConfiguration(Table table) {
            IllegalItemSelection.buildTable(
                    IllegalItemSource.this,
                    table,
                    content.items(),
                    () -> outputItem,
                    this::configure,
                    selectionRows,
                    selectionColumns
            );
        }
    }
}