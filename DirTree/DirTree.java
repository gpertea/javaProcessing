import java.io.File;
import java.util.Stack;

class DirNode {
  boolean visited;
  File dir;
  public DirNode(File d) {
	   dir = d;
	   visited = false;
  }
}

public class DirTree {
	static Stack<String> path;
	public static void printIndented(File f) {
		String s=f.getParentFile().getPath();
    	System.out.print(String.format("%0" + (s.length()-2) + "d", 0).replace("0", " "));
		System.out.println("+--" + f.getName());
	}
	
	public static void listFilesForFolder(File folder) {
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
		Stack<File> rstack=new Stack<File>();
	    for (File entry : folder.listFiles()) {
	        if (entry.isDirectory()) {
	        	rstack.push(entry);
	        } else {
	        	printIndented(entry);
	        }
	    }
        while (!rstack.isEmpty()) {
        	File subd=rstack.pop();
        	listFilesForFolder(subd);
        }
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
		File startDir=new File("d:/temp");
		System.out.println(">"+startDir.getPath());
        listFilesForFolder(startDir);
        System.out.println("-------------------------------------");
		System.out.println(">"+startDir.getPath());
        path.clear();
        treeTraversal(new File("d:/temp"));        
	}

}
