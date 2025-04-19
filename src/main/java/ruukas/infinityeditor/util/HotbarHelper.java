package ruukas.infinityeditor.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class HotbarHelper {

    public static void setItemInFirstHotbarSlot(EntityPlayer player, ItemStack item) {
        if (player != null) {
            if (item != null && !item.isEmpty()) {
                ItemStack itemStack = item.copy();
                player.inventory.setInventorySlotContents(0, itemStack); // Slot 0 is the first hotbar slot

                // Optional: Force client-side inventory update for immediate visual feedback
                if (!player.world.isRemote && player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP) player).sendSlotContents(player.inventoryContainer, 0, itemStack);
                }
            } else {
                // Handle the case where the provided item is null or empty
                player.inventory.setInventorySlotContents(0, ItemStack.EMPTY);
                if (!player.world.isRemote && player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP) player).sendSlotContents(player.inventoryContainer, 0, ItemStack.EMPTY);
                }
            }
        }
    }
}