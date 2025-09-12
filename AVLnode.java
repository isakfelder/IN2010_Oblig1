public class AVLnode {
   int data;
   AVLnode right = null;
   AVLnode left = null;
   int dybde;
   AVLnode parent = null;

   public AVLnode(int data) {
      this.data = data;
      dybde = 0;
   }
}