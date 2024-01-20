package cn.originmc.plugins.magicpaper.util.item;


import org.bukkit.inventory.ItemStack;


public enum DataType {
    INT,FLOAT,DOUBLE,STRING,BOOLEAN,ITEMSTACK,LONG,UUID,SHORT,ITEMSTACKARRAY,INTARRAY,BYTE,BYTEARRAY,FORMATTEXT,KNOWN;

    /**
     * 将对象转换为枚举
     * @param object 数据对象
     * @return 枚举
     */
    public static DataType getType(Object object){
        if (object instanceof Integer){
            return INT;
        }else if (object instanceof Float){
            return FLOAT;
        }else if (object instanceof Double){
            return DOUBLE;
        }else if (object instanceof String){
            return STRING;
        }else if (object instanceof Boolean){
            return BOOLEAN;
        }else if (object instanceof ItemStack){
            return ITEMSTACK;
        }else if (object instanceof Long){
            return LONG;
        }else if (object instanceof java.util.UUID){
            return UUID;
        }else if (object instanceof Short){
            return SHORT;
        }else if (object instanceof ItemStack[]){
            return ITEMSTACKARRAY;
        }else if (object instanceof Integer[]){
            return INTARRAY;
        }else if (object instanceof Byte){
            return BYTE;
        }else if (object instanceof Byte[]){
            return BYTEARRAY;
        }else {
            return KNOWN;
        }
    }
    public static Object stringToObject(String s,DataType dataType){
        switch (dataType){
            case INT:{
                return Integer.parseInt(s);
            }
            case BYTE:{
                return Byte.parseByte(s);
            }
            case LONG:{
                return Long.parseLong(s);
            }
            case UUID:{
                return java.util.UUID.fromString(s);
            }
            case FLOAT:{
                return Float.parseFloat(s);
            }
            case SHORT:{
                return Short.parseShort(s);
            }
            case DOUBLE:{
                return Double.parseDouble(s);
            }
            case STRING:{
                return s;
            }
            case BOOLEAN:{
                return Boolean.parseBoolean(s);
            }
            case ITEMSTACK:{
                return new NBTItem(s).getItemStack();
            }
            default:{
                return null;
            }
        }
    }
}
