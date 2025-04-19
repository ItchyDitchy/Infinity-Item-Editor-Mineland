package ruukas.infinityeditor.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class TargetBlockHelper {

    public static Block getTargetBlock(EntityPlayer player, double range) {
        Vec3d origin = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        Vec3d look = player.getLookVec();
        Vec3d dest = origin.addVector(look.x * range, look.y * range, look.z * range);
        RayTraceResult result = player.world.rayTraceBlocks(origin, dest, false, false, true);

        if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos blockPos = result.getBlockPos();
            return player.world.getBlockState(blockPos).getBlock();
        }

        return null;
    }

    public static EnumFacing getTargetBlockFace(EntityPlayer player, double range) {
        Vec3d origin = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        Vec3d look = player.getLookVec();
        Vec3d dest = origin.addVector(look.x * range, look.y * range, look.z * range);
        RayTraceResult result = player.world.rayTraceBlocks(origin, dest, false, false, true);

        if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
            return result.sideHit;
        }

        return null;
    }

    public static RayTraceResult getRayTraceResult(EntityPlayer player, double range) {
        Vec3d origin = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        Vec3d look = player.getLookVec();
        Vec3d dest = origin.addVector(look.x * range, look.y * range, look.z * range);
        return player.world.rayTraceBlocks(origin, dest, false, false, true);
    }
}
