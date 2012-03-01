package katr20.nbt2.uc;
import java.io.IOException;

import katr20.nbt2.NBTBase;
import katr20.nbt2.NBTLibrary;
import katr20.nbt2.NBTString;

/*
 * A class showing the base uses of NBT2.
 */
public class NBTUseCase {
    public static void main(String[] args) throws IOException {
	/*
	 * Create an NBTLibrary object using a path to the file "test"
	 * and don't read all the tags from the file, hence the boolean
	 * being false. This'll make it so the library is essentially
	 * empty.
	 */
	NBTLibrary lib = new NBTLibrary("test", false);
	/*
	 * Even though we ignored reading the tags upon instantiation,
	 * we'll read them now, just to show it off.
	 */
	lib.readTags();
	/*
	 * Create a tag containing a String and write it to the library.
	 */
	NBTString stringTag = new NBTString("string", "value");
	lib.putTag(stringTag);
	/*
	 * Get the same tag back pointlessly using the tag's previous key.
	 */
	NBTBase sameTag = lib.getTag("string");
	/*
	 * Output the value.
	 */
	System.out.println(sameTag.getValue()); /*Outputs "value"
	/*
	 * Write all of the tags in the library to the file.
	 */
	lib.writeTags();
    }

}
