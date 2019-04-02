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


     static ObjectClass generateNew(ObjectClass objectClass){

         if (getObjectType(objectClass)){
             RectangleObject rectangleObject = (RectangleObject) objectClass;
             int height = rectangleObject.getValueHeight()-((rectangleObject.getValueHeight()*10)/100);
             int width = rectangleObject.getValueWidth()-((rectangleObject.getValueWidth()*10)/100);
             return new RectangleObject(height,width);
         }else{
                 CirclObject circleObject = (CirclObject) objectClass;
                 int radius = (circleObject.getRadius()-(circleObject.getRadius()*10/100));
             return new CirclObject(radius);

         }
    }
//
//    static com.ObjectClass generateNewSize(com.ObjectClass objectClass , int Height, int Width, int Radius){
//
//        if (getObjectType(objectClass)){
//            RectangleObject ro = new RectangleObject();
//
//            ro.setValueHeight(Height);
//            ro.setValueWidth (Width);
//            return ro;
//        }else{
//            com.CirclObject circle = new com.CirclObject();
//            circle.setRadius(Radius);
//            return circle;
//
//        }
//    }

    static  boolean getObjectType(ObjectClass objectClass){

        return (objectClass instanceof RectangleObject);
    }
}
