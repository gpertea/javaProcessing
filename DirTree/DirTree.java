import java.io.File;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class DirTree {
	static Stack<String> path;
	public static void printIndented(File f) {
		String s=f.getParentFile().getPath();
    	System.out.print(String.format("% " + (s.length()-2) + "d", 0).substring(0,s.length()-3));
    	if (f.isDirectory())
		    System.out.println("+--[" + f.getName()+"] <- "+path.toString().replace(", ", "/"));
    	else System.out.println("+--" + f.getName());
	}
	
	
	public static void listRecursive(File folder) {
		if (!folder.isDirectory()) {
            System.out.println("ERROR: '"+ folder.getName()+ "' is not a folder!");
            System.exit(1);
		}
		path.push(folder.getName());
		printIndented(folder);
		for (File entry : folder.listFiles()) {
	        if (entry.isDirectory()) 
	        	listRecursive(entry); //descend into the sub-directory
	        else printIndented(entry); //a file, just print it
		}
		path.pop();
	}
	
	public static void listDirTree(File folder) {
		if (!folder.isDirectory()) {
            System.out.println("ERROR: '"+ folder.getName()+ "' not a folder!");
            System.exit(1);
		}
		if (path.isEmpty()) {
			path.push(folder.getPath());
		}
		else {
			printIndented(folder);
			path.push(folder.getName());
		}
		//we want to show folders first, always
		Queue<File> dq=new LinkedList<>(); //folder queue
		Queue<File> fq=new LinkedList<>(); //files queue
	    for (File entry : folder.listFiles()) {
	        if (entry.isDirectory()) {
	        	dq.add(entry);
	        } else {
	        	//printIndented(entry);
	        	fq.add(entry);
	        }
	    }
        while (!dq.isEmpty()) {
        	File dir=dq.remove();
        	listDirTree(dir);
        }
        while (!fq.isEmpty()) {
        	File f=fq.remove();
        	printIndented(f);
        }
        //keep track of the directory branch we're on
	    path.pop();
	}

	public static void treeTraversal(File folder) {
		if (!folder.isDirectory()) {
            System.out.println("ERROR: '"+ folder.getName()+ "' not a folder!");
            System.exit(1);
		}
		Stack<File> dirstack = new Stack<File>();
		boolean firstTime=true;
		dirstack.push(folder);
		while (!dirstack.isEmpty()) {
			File tdir=dirstack.pop();
			if (firstTime) firstTime=false;
			else printIndented(tdir);
		    for (File entry : tdir.listFiles()) {
		        if (entry.isDirectory()) {
		            dirstack.push(entry);
		        } else { //regular file
		            printIndented(entry); //print file name
		        }
		    }	    
		} //big loop
	}
	
	public static void main(String[] args) {
		path=new Stack<String>();
		File startDir=new File("d:/dirtree");
		System.out.println(">"+startDir.getPath());
        listRecursive(startDir);
        /*
        System.out.println("-------------------------------------");
		System.out.println(">"+startDir.getPath());
        path.clear();
        treeTraversal(new File("d:/dirtree"));
        */        
	}

}
