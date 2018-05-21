package fr.chokearth.rpm.object.block.chestLock;

import fr.chokearth.rpm.Main;
import fr.chokearth.rpm.init.BlockInit;
import fr.chokearth.rpm.init.ItemInit;
import fr.chokearth.rpm.util.inter.IHasModel;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChestLockBlock extends BlockChest implements IHasModel {
    public ChestLockBlock(String name, CreativeTabs tabs) {
        super(Type.BASIC);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(tabs);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (((TileEntityChestLock)worldIn.getTileEntity(pos)).getOwner().equals(playerIn.getName()))
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        else return false;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        ((TileEntityChestLock)worldIn.getTileEntity(pos)).setOwner(placer.getName());
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityChestLock();
    }
}
