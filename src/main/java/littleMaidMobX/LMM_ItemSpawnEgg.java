package littleMaidMobX;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LMM_ItemSpawnEgg extends Item
{
	public LMM_ItemSpawnEgg()
	{
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, BlockPos par46X, EnumFacing par7, float par8, float par9, float par10)
	{
		if (par3World.isRemote)
		{
			return true;
		}
		Block block = par3World.getBlockState(par46X).getBlock();
		int par4 = par46X.getX(); int par5 = par46X.getY() + 1; int par6 = par46X.getZ();
		/*
		par4 += Facing.offsetsXForSide[par7];
		par5 += Facing.offsetsYForSide[par7];
		par6 += Facing.offsetsZForSide[par7];
		*/
		double d0 = 0.0D;

		if (par7 == EnumFacing.UP && block.getRenderType() == 11)
		{
			d0 = 0.5D;
		}

		Entity entity = spawnMaid(par3World, par4 + 0.5D, par5 + d0, par6 + 0.5D);

		if (entity != null)
		{
			if (entity instanceof EntityLivingBase && par1ItemStack.hasDisplayName())
			{
				((EntityLiving)entity).setCustomNameTag(par1ItemStack.getDisplayName());
			}

			if (!par2EntityPlayer.capabilities.isCreativeMode)
			{
				--par1ItemStack.stackSize;
			}
		}

		return true;
	}

	public static Entity spawnMaid(World par0World, double par2, double par4, double par6)
	{
		EntityLiving entityliving = null;
		try {
			entityliving = new LMM_EntityLittleMaid(par0World);

			entityliving.setLocationAndAngles(par2, par4, par6, MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F), 0.0F);
			((LMM_EntityLittleMaid)entityliving).setTextureNames();
			((LMM_EntityLittleMaid) entityliving).onSpawnWithEgg();
			par0World.spawnEntityInWorld(entityliving);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return entityliving;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1, CreativeTabs par2, List par3)
	{
		par3.add(new ItemStack(par1, 1));
	}

}
