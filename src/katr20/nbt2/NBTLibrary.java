package katr20.nbt2;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class NBTLibrary {
    private HashMap<String, NBTBase> tagMap = new HashMap<String, NBTBase>();
    private File file;

    public static NBTLibrary copyOf(NBTLibrary lib) throws IOException {
	NBTLibrary copy = new NBTLibrary(lib.file, false);
	for (NBTBase tag : lib.getTags()) {
	    copy.putTag(tag);
	}
	return copy;
    }

    public static NBTLibrary fromTagArray(File to, NBTBase[] bases)
	    throws IOException {
	NBTLibrary lib = new NBTLibrary(to, false);
	for (NBTBase tag : bases) {
	    lib.putTag(tag);
	}
	return lib;
    }

    public static NBTLibrary allTagsOfType(File to, NBTBase[] tags,
	    NBTTagType type) throws IOException {
	ArrayList<NBTBase> bases = new ArrayList<NBTBase>();
	for (NBTBase base : tags) {
	    if (base.getTagType().equals(type)) {
		bases.add(base);
	    }
	}
	return fromTagArray(to, bases.toArray(new NBTBase[bases.size()]));
    }

    public NBTLibrary(String path) throws IOException {
	this(path, true);
    }

    public NBTLibrary(File file) throws IOException {
	this(file, true);
    }

    public NBTLibrary(String path, boolean read) throws IOException {
	this(new File(path), read);
    }

    public NBTLibrary(File file, boolean read) throws IOException {
	this.file = file;

	if (read) {
	    readTags();
	}
    }

    public void clearTags() {
	this.tagMap.clear();
    }

    public void readTags() throws IOException {
	FileInputStream reader = null;
	DataInput input = null;
	try {
	    if ((this.file != null) && this.file.exists()) {
		input = new DataInputStream((reader = new FileInputStream(
			this.file)));
		NBTBase base;
		while ((base = NBTBase.readTag(input)) != null) {
		    this.tagMap.put(base.getKey(), base);
		}
	    }
	} finally {
	    if (reader != null) {
		reader.close();
	    }
	}
    }

    public void writeTags() throws IOException {
	FileOutputStream writer = null;
	DataOutput output = null;
	try {
	    output = new DataOutputStream(new FileOutputStream(this.file));
	    for (NBTBase tag : this.tagMap.values()) {
		NBTBase.writeTag(tag, output);
	    }
	} finally {
	    if (writer != null) {
		writer.close();
	    }
	}
    }

    public NBTBase getTag(String key) {
	return this.tagMap.get(key);
    }

    public void putTag(NBTBase tag) {
	this.tagMap.put(tag.getKey(), tag);
    }

    public void removeTag(NBTBase tag) {
	removeTag(tag.getKey());
    }

    public void removeTag(String key) {
	if (this.tagMap.containsKey(key)) {
	    this.tagMap.remove(key);
	}
    }

    public int tagCount() {
	return this.tagMap.size();
    }

    public NBTBase[] getTags() {
	return this.tagMap.values().toArray(new NBTBase[this.tagMap.size()]);
    }

    public String[] getTagKeys() {
	return this.tagMap.keySet().toArray(new String[this.tagMap.size()]);
    }

    public void copyFrom(NBTLibrary library) {
	for (NBTBase base : library.getTags()) {
	    putTag(base);
	}
    }

    public File getFile() {
	return this.file;
    }

    public void setFile(File file) {
	this.file = file;
    }
}
