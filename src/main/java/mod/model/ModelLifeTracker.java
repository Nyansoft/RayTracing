package mod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLifeTracker extends ModelBase 
{
	public ModelRenderer shape1;
	public ModelRenderer shape11;
	public ModelRenderer shape111;
	public ModelRenderer shape2;
	public ModelRenderer shape22;
	public ModelRenderer shape222;
	public ModelRenderer shape3;
	public ModelRenderer shape33;
	public ModelRenderer shape333;
	public ModelRenderer shape4;
	public ModelRenderer shape44;
	public ModelRenderer shape444;
	public ModelRenderer axis;
	public ModelRenderer base;

	public ModelLifeTracker() 
	{
		textureWidth = 64;
		textureHeight = 32;

		shape1 = new ModelRenderer(this, 42, 9);
		shape1.addBox(-3F, -3F, -3F, 1, 6, 6);
		shape1.setRotationPoint(3F, 3F, 3F);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0F, 3.141593F, 0F);
		
		shape11 = new ModelRenderer(this, 32, 0);
		shape11.addBox(-1F, -5F, 3F, 2, 4, 1);
		shape11.setRotationPoint(3F, 3F, 3F);
		shape11.setTextureSize(64, 32);
		shape11.mirror = true;
		setRotation(shape11, 0F, 1.570796F, 0F);
		
		shape111 = new ModelRenderer(this, 32, 5);
		shape111.addBox(-1F, -5F, 2F, 2, 1, 1);
		shape111.setRotationPoint(3F, 3F, 3F);
		shape111.setTextureSize(64, 32);
		shape111.mirror = true;
		setRotation(shape111, 0F, 1.570796F, 0F);
		
		shape2 = new ModelRenderer(this, 0, 9);
		shape2.addBox(-3F, -3F, -3F, 1, 6, 6);
		shape2.setRotationPoint(3F, 3F, 3F);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0F, 1.570796F, 0F);
		
		shape22 = new ModelRenderer(this, 38, 0);
		shape22.addBox(-1F, -5F, 3F, 2, 4, 1);
		shape22.setRotationPoint(3F, 3F, 3F);
		shape22.setTextureSize(64, 32);
		shape22.mirror = true;
		setRotation(shape22, 0F, 0F, 0F);
		
		shape222 = new ModelRenderer(this, 38, 5);
		shape222.addBox(-1F, -5F, 2F, 2, 1, 1);
		shape222.setRotationPoint(3F, 3F, 3F);
		shape222.setTextureSize(64, 32);
		shape222.mirror = true;
		setRotation(shape222, 0F, 0F, 0F);
		
		shape3 = new ModelRenderer(this, 14, 9);
		shape3.addBox(-3F, -3F, -3F, 1, 6, 6);
		shape3.setRotationPoint(3F, 3F, 3F);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0F, 0F, 0F);
		
		shape33 = new ModelRenderer(this, 44, 0);
		shape33.addBox(-1F, -5F, 3F, 2, 4, 1);
		shape33.setRotationPoint(3F, 3F, 3F);
		shape33.setTextureSize(64, 32);
		shape33.mirror = true;
		setRotation(shape33, 0F, -1.570796F, 0F);
		
		shape333 = new ModelRenderer(this, 44, 5);
		shape333.addBox(-1F, -5F, 2F, 2, 1, 1);
		shape333.setRotationPoint(3F, 3F, 3F);
		shape333.setTextureSize(64, 32);
		shape333.mirror = true;
		setRotation(shape333, 0F, -1.570796F, 0F);
		
		shape4 = new ModelRenderer(this, 28, 9);
		shape4.addBox(-3F, -3F, -3F, 1, 6, 6);
		shape4.setRotationPoint(3F, 3F, 3F);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0F, -1.570796F, 0F);
		
		shape44 = new ModelRenderer(this, 50, 0);
		shape44.addBox(-1F, -5F, 3F, 2, 4, 1);
		shape44.setRotationPoint(3F, 3F, 3F);
		shape44.setTextureSize(64, 32);
		shape44.mirror = true;
		setRotation(shape44, 0F, 3.141593F, 0F);
		
		shape444 = new ModelRenderer(this, 50, 5);
		shape444.addBox(-1F, -5F, 2F, 2, 1, 1);
		shape444.setRotationPoint(3F, 3F, 3F);
		shape444.setTextureSize(64, 32);
		shape444.mirror = true;
		setRotation(shape444, 0F, 3.141593F, 0F);
		
		axis = new ModelRenderer(this, 0, 21);
		axis.addBox(-1F, -2F, -1F, 2, 4, 2);
		axis.setRotationPoint(3F, 3F, 3F);
		axis.setTextureSize(64, 32);
		axis.mirror = true;
		setRotation(axis, 0F, 0F, 0F);
		
		base = new ModelRenderer(this, 0, 0);
		base.addBox(-4F, 0F, -4F, 8, 1, 8);
		base.setRotationPoint(3F, 8F, 3F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
	}

	public void render() 
	{
		float f5 = 0.0625F;
		shape1.render(f5);
		shape11.render(f5);
		shape111.render(f5);
		shape2.render(f5);
		shape22.render(f5);
		shape222.render(f5);
		shape3.render(f5);
		shape33.render(f5);
		shape333.render(f5);
		shape4.render(f5);
		shape44.render(f5);
		shape444.render(f5);
		axis.render(f5);
		base.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) 
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
