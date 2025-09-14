public class AVLnode {
   int data;
   int dybde;
   AVLnode right = null;
   AVLnode left = null;
   AVLnode parent = null;

   public AVLnode(int data) {
      this.data = data;
      dybde = 0;
   }
   
}