package org.abstruck.mc.cybermc.block;


import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.abstruck.mc.cybermc.block.tileentity.OperatingTableTileEntity;
import org.jetbrains.annotations.NotNull;

/**
 * @author Astrack
 */
public class OperatingTable extends BedBlock {
    public OperatingTable() {
        super(DyeColor.WHITE,Properties.of(Material.STONE).harvestLevel(2));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    @NotNull
    public ActionResultType use(@NotNull BlockState blockState, @NotNull World world, @NotNull BlockPos blockPos, @NotNull PlayerEntity player, @NotNull Hand hand, @NotNull BlockRayTraceResult blockRayTraceResult) {
        //判断是否是客户端玩家使用主手互动
        if (world.isClientSide && hand == Hand.MAIN_HAND){
            //获取手术台的方块实体
            OperatingTableTileEntity operatingTableTileEntity = (OperatingTableTileEntity) world.getBlockEntity(blockPos);
            NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) operatingTableTileEntity, packetBuffer->{
                assert operatingTableTileEntity != null;
                //把方块实体的坐标放进packetBuffer里
                packetBuffer.writeBlockPos(operatingTableTileEntity.getBlockPos());
            });
        }

        return ActionResultType.SUCCESS;
    }
}
