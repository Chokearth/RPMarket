package fr.chokearth.rpm.object.block.base;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBaseAdjustableHorizontal extends BlockBase{

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    private boolean rotateAssistance = true;
    private boolean fullBlock = true;
    private double axisAlignedBBTab[] = new double[6];

    public BlockBaseAdjustableHorizontal(String name, Material material, CreativeTabs creativeTabs, boolean rotateAssistance) {
        super(name, material, creativeTabs);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.rotateAssistance = rotateAssistance;
    }

    public BlockBaseAdjustableHorizontal(String name, Material material, CreativeTabs creativeTabs, boolean rotateAssistance, boolean fullblock, double[] axisAlignedBBTab) {
        super(name, material, creativeTabs);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.rotateAssistance = rotateAssistance;
        this.axisAlignedBBTab = axisAlignedBBTab;

        this.fullBlock = fullblock;
        //this.axisAlignedBB = new AxisAlignedBB(axisAlignedBBTab[0], axisAlignedBBTab[1], axisAlignedBBTab[2], axisAlignedBBTab[3], axisAlignedBBTab[4], axisAlignedBBTab[5]);
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {

        if(!worldIn.isRemote){
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = (EnumFacing)state.getValue(FACING);

            if(rotateAssistance){
                if(face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
                else if(face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
                else if(face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
                else if(face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
            }
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);

        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta);
        if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumFacing face = (EnumFacing)state.getValue(FACING);

        if(face == EnumFacing.NORTH) return new AxisAlignedBB(this.axisAlignedBBTab[0], this.axisAlignedBBTab[1], this.axisAlignedBBTab[2], this.axisAlignedBBTab[3], this.axisAlignedBBTab[4], this.axisAlignedBBTab[5]);
        else if(face == EnumFacing.SOUTH) return new AxisAlignedBB(1-this.axisAlignedBBTab[0], this.axisAlignedBBTab[1], 1-this.axisAlignedBBTab[2], 1-this.axisAlignedBBTab[3], this.axisAlignedBBTab[4], 1-this.axisAlignedBBTab[5]);
        else if(face == EnumFacing.WEST) return new AxisAlignedBB(this.axisAlignedBBTab[2], this.axisAlignedBBTab[1], 1-this.axisAlignedBBTab[3], this.axisAlignedBBTab[5], this.axisAlignedBBTab[4], 1-this.axisAlignedBBTab[0]);
        else if(face == EnumFacing.EAST) return new AxisAlignedBB(1-this.axisAlignedBBTab[2], this.axisAlignedBBTab[1], this.axisAlignedBBTab[3], 1-this.axisAlignedBBTab[5], this.axisAlignedBBTab[4], this.axisAlignedBBTab[0]);

        return new AxisAlignedBB(this.axisAlignedBBTab[0], this.axisAlignedBBTab[1], this.axisAlignedBBTab[2], this.axisAlignedBBTab[3], this.axisAlignedBBTab[4], this.axisAlignedBBTab[5]);
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
