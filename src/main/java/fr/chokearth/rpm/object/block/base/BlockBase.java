package fr.chokearth.rpm.object.block.base;

import fr.chokearth.rpm.Main;
import fr.chokearth.rpm.init.BlockInit;
import fr.chokearth.rpm.init.ItemInit;
import fr.chokearth.rpm.util.inter.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockBase extends Block implements IHasModel {
    private boolean fullBlock = true;
    private AxisAlignedBB axisAlignedBB = new AxisAlignedBB(0,0,0,1,1,1);

    public BlockBase(String name, Material material, CreativeTabs tabs){
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    public BlockBase(String name, Material material, CreativeTabs tabs, boolean fullblock, double[] axisAlignedBBTab){
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(tabs);

        this.fullBlock = fullblock;
        this.axisAlignedBB = new AxisAlignedBB(axisAlignedBBTab[0], axisAlignedBBTab[1], axisAlignedBBTab[2], axisAlignedBBTab[3], axisAlignedBBTab[4], axisAlignedBBTab[5]);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return axisAlignedBB;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return fullBlock;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return fullBlock;
    }
}
