package ruukas.infinityeditor.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class ShulkerBoxHelper {

    public static ItemStack addItemToShulkerBox(ItemStack shulkerBoxStack, ItemStack itemToAdd) {
        if (shulkerBoxStack == null || shulkerBoxStack.isEmpty() || itemToAdd == null || itemToAdd.isEmpty()) {
            return shulkerBoxStack; // Or handle the null/empty cases as needed
        }

        if (!shulkerBoxStack.getItem().getRegistryName().toString().contains("shulker_box")) {
            System.err.println("Provided ItemStack is not a Shulker Box.");
            return shulkerBoxStack;
        }

        NBTTagCompound shulkerBoxNBT = shulkerBoxStack.getTagCompound();
        if (shulkerBoxNBT == null) {
            shulkerBoxNBT = new NBTTagCompound();
            shulkerBoxStack.setTagCompound(shulkerBoxNBT);
        }

        NBTTagCompound itemsTag = shulkerBoxNBT.getCompoundTag("BlockEntityTag");
        if (!itemsTag.hasKey("Items", Constants.NBT.TAG_LIST)) {
            itemsTag.setTag("Items", new NBTTagList());
        }
        NBTTagList itemList = itemsTag.getTagList("Items", Constants.NBT.TAG_COMPOUND);

        NBTTagCompound itemToAddNBT = new NBTTagCompound();
        itemToAdd.writeToNBT(itemToAddNBT);
        itemToAddNBT.setByte("Slot", (byte) itemList.tagCount());
        itemList.appendTag(itemToAddNBT);

        itemsTag.setTag("Items", itemList);
        shulkerBoxNBT.setTag("BlockEntityTag", itemsTag);
        shulkerBoxStack.setTagCompound(shulkerBoxNBT);

        return shulkerBoxStack;
    }
}