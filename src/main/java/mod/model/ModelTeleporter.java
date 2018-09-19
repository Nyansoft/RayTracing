package mod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTeleporter extends ModelBase
{
    public ModelRenderer box1;
    public ModelRenderer box2;
    public ModelRenderer box3;
    public ModelRenderer box4;
    public ModelRenderer box5;
    public ModelRenderer box6;
    public ModelRenderer outteraxis;
    public ModelRenderer inneraxis;
    public ModelRenderer plate;
    public ModelRenderer lowerplate;
    public ModelRenderer magnum1;
    public ModelRenderer magnum2;
  
    public ModelTeleporter()
    {
    	this.textureWidth = 64;
    	this.textureHeight = 64;
    
    	this.box1 = new ModelRenderer(this, 0, 0);
    	this.box1.addBox(-8F, -1F, -8F, 16, 1, 16);
    	this.box1.setRotationPoint(0F, 0F, 0F);
    	this.box1.setTextureSize(64, 64);
    	this.box1.mirror = true;
      
    	this.box2 = new ModelRenderer(this, 0, 17);
    	this.box2.addBox(-1F, -2F, -1F, 2, 3, 2);
    	this.box2.setRotationPoint(6F, 0F, -6F);
    	this.box2.setTextureSize(64, 64);
    	this.box2.mirror = true;
      
    	this.box3 = new ModelRenderer(this, 0, 17);
    	this.box3.addBox(-1F, -2F, -1F, 2, 3, 2);
      	this.box3.setRotationPoint(6F, 0F, 6F);
      	this.box3.setTextureSize(64, 64);
      	this.box3.mirror = true;
      
      	this.box4 = new ModelRenderer(this, 0, 17);
      	this.box4.addBox(-1F, -2F, -1F, 2, 3, 2);
      	this.box4.setRotationPoint(-6F, 0F, 6F);
      	this.box4.setTextureSize(64, 64);
      	this.box4.mirror = true;
      
      	this.box5 = new ModelRenderer(this, 0, 17);
      	this.box5.addBox(-1F, -2F, -1F, 2, 3, 2);
      	this.box5.setRotationPoint(-6F, 0F, -6F);
      	this.box5.setTextureSize(64, 64);
      	this.box5.mirror = true;
      
      	this.box6 = new ModelRenderer(this, 8, 17);
      	this.box6.addBox(-3F, 0F, -3F, 6, 1, 6);
      	this.box6.setRotationPoint(0F, -2F, 0F);
      	this.box6.setTextureSize(64, 64);
      	this.box6.mirror = true;
      
      	this.outteraxis = new ModelRenderer(this, 52, 17);
      	this.outteraxis.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
      	this.outteraxis.setRotationPoint(0F, -5F, 0F);
      	this.outteraxis.setTextureSize(64, 64);
      	this.outteraxis.mirror = true;
      	this.setRotation(outteraxis, 0F, 0.7853982F, 0F);
      
      	this.inneraxis = new ModelRenderer(this, 32, 23);
      	this.inneraxis.addBox(-1F, 0F, -1F, 2, 1, 2);
      	this.inneraxis.setRotationPoint(0F, -5.5F, 0F);
      	this.inneraxis.setTextureSize(64, 64);
      	this.inneraxis.mirror = true;
      	this.setRotation(inneraxis, 0F, 0.7853982F, 0F);
      
      	this.plate = new ModelRenderer(this, 0, 26);
      	this.plate.addBox(-10F, 0F, -2.5F, 20, 1, 5);
      	this.plate.setRotationPoint(0F, -4.5F, 0F);
      	this.plate.setTextureSize(64, 64);
      	this.plate.mirror = true;
      
      	this.lowerplate = new ModelRenderer(this, 12, 32);
      	this.lowerplate.addBox(-6F, 0F, -1F, 12, 1, 2);
      	this.lowerplate.setRotationPoint(0F, -3.5F, 0F);
      	this.lowerplate.setTextureSize(64, 64);
      	this.lowerplate.mirror = true;
      
      	this.magnum1 = new ModelRenderer(this, 0, 32);
      	this.magnum1.addBox(4.5F, 0F, -1.5F, 3, 1, 3);
      	this.magnum1.setRotationPoint(0F, -5.5F, 0F);
      	this.magnum1.setTextureSize(64, 64);
      	this.magnum1.mirror = true;
      
      	this.magnum2 = new ModelRenderer(this, 0, 32);
      	this.magnum2.addBox(-7.5F, 0F, -1.5F, 3, 1, 3);
      	this.magnum2.setRotationPoint(0F, -5.5F, 0F);
      	this.magnum2.setTextureSize(64, 64);
      	this.magnum2.mirror = true;
    }	
  
    public void renderAll()
    {
    	float f5 = 0.0625F;
    	box1.render(f5);
    	box2.render(f5);
    	box3.render(f5);
    	box4.render(f5);
    	box5.render(f5);
    	box6.render(f5);
    	outteraxis.render(f5);
    	inneraxis.render(f5);
    	plate.render(f5);
    	lowerplate.render(f5);
    	magnum1.render(f5);
    	magnum2.render(f5);
    }
  
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    }
    
    public void translate(ModelRenderer model, float dx, float dy, float dz)
    {
    	model.offsetX += dx;
    	model.offsetY += dy;
    	model.offsetZ += dz;
    }
}
