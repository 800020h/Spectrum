package de.dafuqs.spectrum.blocks.mob_head.models;

import de.dafuqs.spectrum.blocks.mob_head.*;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.*;

public class VillagerHeadModel extends SpectrumHeadModel {

    public VillagerHeadModel(ModelPart root) {
        super(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData ModelData = new ModelData();
        ModelPartData ModelPartData = ModelData.getRoot();

        ModelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create()
                .uv(0, 0).cuboid(-12.0F, -10.0F, 4.0F, 8.0F, 10.0F, 8.0F)
                .uv(24, 0).cuboid(-9.0F, -3.0F, 2.0F, 2.0F, 4.0F, 2.0F), ModelTransform.NONE);

        return TexturedModelData.of(ModelData, 64, 64);
    }

}