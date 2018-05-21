package fr.chokearth.rpm.object.item;

import fr.chokearth.rpm.init.BlockInit;
import fr.chokearth.rpm.object.block.base.BlockBaseAdjustableHorizontal;
import fr.chokearth.rpm.object.item.base.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPlaceStand extends ItemBase{

    public ItemPlaceStand(String name, CreativeTabs creativeTabs) {
        super(name, creativeTabs);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        BlockPos pos1 = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());
        BlockPos pos2 = new BlockPos(pos.getX(), pos.getY()+2, pos.getZ());
        ItemStack itemstack = player.getHeldItem(hand);
        Block storageBlock;
        Block sellBlock;

        switch (this.name){
            case "place_stand_oak":
                storageBlock = BlockInit.STAND_STORAGE_BLOCK_OAK;
                sellBlock = BlockInit.STAND_SELL_BLOCK_OAK;
                break;
            case "place_stand_spruce":
                storageBlock = BlockInit.STAND_STORAGE_BLOCK_SPRUCE;
                sellBlock = BlockInit.STAND_SELL_BLOCK_SPRUCE;
                break;
            case "place_stand_birch":
                storageBlock = BlockInit.STAND_STORAGE_BLOCK_BIRCH;
                sellBlock = BlockInit.STAND_SELL_BLOCK_BIRCH;
                break;
            case "place_stand_jungle":
                storageBlock = BlockInit.STAND_STORAGE_BLOCK_JUNGLE;
                sellBlock = BlockInit.STAND_SELL_BLOCK_JUNGLE;
                break;
            case "place_stand_acacia":
                storageBlock = BlockInit.STAND_STORAGE_BLOCK_ACACIA;
                sellBlock = BlockInit.STAND_SELL_BLOCK_ACACIA;
                break;
            case "place_stand_darkoak":
                storageBlock = BlockInit.STAND_STORAGE_BLOCK_DARKOAK;
                sellBlock = BlockInit.STAND_SELL_BLOCK_DARKOAK;
                break;
            default:
                storageBlock = BlockInit.STAND_STORAGE_BLOCK_OAK;
                sellBlock = BlockInit.STAND_SELL_BLOCK_OAK;
        }

        if (worldIn.mayPlace(storageBlock, pos1, false, facing, player) && worldIn.mayPlace(sellBlock, pos2, false, facing, player)){

            IBlockState storageState = storageBlock.getDefaultState();
            IBlockState sellState = sellBlock.getDefaultState();

            worldIn.setBlockState(pos1, storageState, 3);
            IBlockState state1 = worldIn.getBlockState(pos1);
            storageBlock.onBlockPlacedBy(worldIn, pos1, state1, player, itemstack);

            worldIn.setBlockState(pos2, sellState, 3);
            IBlockState state2 = worldIn.getBlockState(pos2);
            sellBlock.onBlockPlacedBy(worldIn, pos2, state2, player, itemstack);

            itemstack.shrink(1);
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
