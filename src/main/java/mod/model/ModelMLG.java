package mod.model;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMLG extends ModelBase 
{
	public ModelRenderer painel;
	public ModelRenderer rotate1;
	public ModelRenderer rotate2;
	public ModelRenderer spector;
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer shape4;
	public ModelRenderer shape14;
	public ModelRenderer shape5;
	public ModelRenderer shape13;
	public ModelRenderer shape6;
	public ModelRenderer shape12;
	public ModelRenderer shape7;
	public ModelRenderer shape11;
	public ModelRenderer shape8;
	public ModelRenderer shape10;
	public ModelRenderer shape9;
	public ModelRenderer shape15;

	public ModelMLG() 
	{
		textureWidth = 256;
		textureHeight = 256;

		painel = new ModelRenderer(this, 60, 0);
		painel.addBox(-4F, -5F, 0F, 8, 5, 0);
		painel.setRotationPoint(0F, -11F, 11.5F);
		painel.setTextureSize(256, 256);
		painel.mirror = true;
		setRotation(painel, -0.5235988F, 0F, 0F);
		rotate1 = new ModelRenderer(this, 0, 0);
		rotate1.addBox(0F, -7F, -7F, 0, 14, 14);
		rotate1.setRotationPoint(0F, 0F, 0F);
		rotate1.setTextureSize(256, 256);
		rotate1.mirror = true;
		setRotation(rotate1, 0F, 0F, 1.570796F);
		rotate2 = new ModelRenderer(this, 30, 0);
		rotate2.addBox(0F, -7F, -7F, 0, 14, 14);
		rotate2.setRotationPoint(0F, 0F, 0F);
		rotate2.setTextureSize(256, 256);
		rotate2.mirror = true;
		setRotation(rotate2, 0F, 0F, 0F);
		spector = new ModelRenderer(this, 60, 7);
		spector.addBox(-3F, -3F, 14F, 6, 6, 0);
		spector.setRotationPoint(0F, 0F, 0F);
		spector.setTextureSize(256, 256);
		spector.mirror = true;
		setRotation(spector, 0F, 0F, 0F);
		shape1 = new ModelRenderer(this, 35, 48);
		shape1.addBox(-1F, -9.5F, -10F, 2, 2, 6);
		shape1.setRotationPoint(0F, 0F, 0F);
		shape1.setTextureSize(256, 256);
		shape1.mirror = true;
		setRotation(shape1, 0F, 0F, 0F);
		shape2 = new ModelRenderer(this, 150, 40);
		shape2.addBox(-10F, -0.5F, -9F, 9, 1, 1);
		shape2.setRotationPoint(0F, 0F, 0F);
		shape2.setTextureSize(256, 256);
		shape2.mirror = true;
		setRotation(shape2, 0F, 0F, 1.570796F);
		shape3 = new ModelRenderer(this, 35, 59);
		shape3.addBox(-1F, -1F, 4F, 2, 2, 6);
		shape3.setRotationPoint(0F, 0F, 0F);
		shape3.setTextureSize(256, 256);
		shape3.mirror = true;
		setRotation(shape3, 0F, 0F, 0F);
		shape4 = new ModelRenderer(this, 54, 60);
		shape4.addBox(-1F, -1F, 12F, 2, 2, 4);
		shape4.setRotationPoint(0F, 0F, 0F);
		shape4.setTextureSize(256, 256);
		shape4.mirror = true;
		setRotation(shape4, 0F, 0F, 0F);
		shape14 = new ModelRenderer(this, 150, 49);
		shape14.addBox(-4F, -10F, 11F, 8, 1, 1);
		shape14.setRotationPoint(0F, 0F, 0F);
		shape14.setTextureSize(256, 256);
		shape14.mirror = true;
		setRotation(shape14, 0F, 0F, 0F);
		shape5 = new ModelRenderer(this, 35, 30);
		shape5.addBox(-4F, -4F, -4F, 8, 8, 8);
		shape5.setRotationPoint(0F, 0F, 0F);
		shape5.setTextureSize(256, 256);
		shape5.mirror = true;
		setRotation(shape5, 0F, 0F, 0F);
		shape13 = new ModelRenderer(this, 68, 30);
		shape13.addBox(-5F, -9F, -7F, 10, 1, 20);
		shape13.setRotationPoint(0F, 0F, 0F);
		shape13.setTextureSize(256, 256);
		shape13.mirror = true;
		setRotation(shape13, 0F, 0F, 0F);
		shape6 = new ModelRenderer(this, 53, 49);
		shape6.addBox(-0.5F, -0.5F, 10F, 1, 1, 2);
		shape6.setRotationPoint(0F, 0F, 0F);
		shape6.setTextureSize(256, 256);
		shape6.mirror = true;
		setRotation(shape6, 0F, 0F, 0F);
		shape12 = new ModelRenderer(this, 130, 30);
		shape12.addBox(-1F, -1F, -10F, 2, 2, 6);
		shape12.setRotationPoint(0F, 0F, 0F);
		shape12.setTextureSize(256, 256);
		shape12.mirror = true;
		setRotation(shape12, 0F, 0F, 0F);
		shape7 = new ModelRenderer(this, 130, 40);
		shape7.addBox(1F, -0.5F, -9F, 8, 1, 1);
		shape7.setRotationPoint(0F, 0F, 0F);
		shape7.setTextureSize(256, 256);
		shape7.mirror = true;
		setRotation(shape7, 0F, 0F, 0F);
		shape11 = new ModelRenderer(this, 130, 45);
		shape11.addBox(-9F, -0.5F, -9F, 8, 1, 1);
		shape11.setRotationPoint(0F, 0F, 0F);
		shape11.setTextureSize(256, 256);
		shape11.mirror = true;
		setRotation(shape11, 0F, 0F, 0F);
		shape8 = new ModelRenderer(this, 130, 49);
		shape8.addBox(-9F, -0.5F, 8F, 8, 1, 1);
		shape8.setRotationPoint(0F, 0F, 0F);
		shape8.setTextureSize(256, 256);
		shape8.mirror = true;
		setRotation(shape8, 0F, 0F, 0F);
		shape10 = new ModelRenderer(this, 68, 53);
		shape10.addBox(8F, -0.5F, -8F, 1, 1, 16);
		shape10.setRotationPoint(0F, 0F, 0F);
		shape10.setTextureSize(256, 256);
		shape10.mirror = true;
		setRotation(shape10, 0F, 0F, 0F);
		shape9 = new ModelRenderer(this, 104, 53);
		shape9.addBox(-9F, -0.5F, -8F, 1, 1, 16);
		shape9.setRotationPoint(0F, 0F, 0F);
		shape9.setTextureSize(256, 256);
		shape9.mirror = true;
		setRotation(shape9, 0F, 0F, 0F);
		shape15 = new ModelRenderer(this, 150, 45);
		shape15.addBox(1F, -0.5F, 8F, 8, 1, 1);
		shape15.setRotationPoint(0F, 0F, 0F);
		shape15.setTextureSize(256, 256);
		shape15.mirror = true;
		setRotation(shape15, 0F, 0F, 0F);
	}

	public void render() 
	{
		float f5 = 0.0625F;
		painel.render(f5);
		rotate1.render(f5);
		rotate2.render(f5);
		spector.render(f5);
		shape1.render(f5);
		shape2.render(f5);
		shape3.render(f5);
		shape4.render(f5);
		shape14.render(f5);
		shape5.render(f5);
		shape13.render(f5);
		shape6.render(f5);
		shape12.render(f5);
		shape7.render(f5);
		shape11.render(f5);
		shape8.render(f5);
		shape10.render(f5);
		shape9.render(f5);
		shape15.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) 
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
