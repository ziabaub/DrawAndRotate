package com;


/**
 * this class made it for
 * generate rectangle with
 * new size minus 10%
 */
 class ObjectGenerating {



    /**
     * set the old value minus
     * 10% from original size
     */


     static ObjectClass generateNew(ObjectClass objectClass,char type){

         if (type=='r'){
             int height = objectClass.getValueHeight()-((objectClass.getValueHeight()*10)/100);
             int width = objectClass.getValueWidth()-((objectClass.getValueWidth()*10)/100);
             return new ObjectClass(height,width);
         }else{
                 int radius = (objectClass.getRadius()-(objectClass.getRadius()*10/100));
             return new ObjectClass(radius);

         }
    }

}
