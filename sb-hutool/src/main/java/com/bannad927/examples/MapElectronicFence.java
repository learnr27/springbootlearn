package com.bannad927.examples;

import java.util.ArrayList;

/**
 * 地图电子围栏
 *
 * @author: chengbinbin
 * @date: 2021.1.6
 **/
public class MapElectronicFence {

    public static void main(String[] args) {
        double px = 118.122858;
        double py = 24.475287;
        ArrayList<Double> polygonXA = new ArrayList<Double>();
        ArrayList<Double> polygonYA = new ArrayList<Double>();

        //118.122858,24.475287(火车站)
        //118.081177,24.645399（厦门北）
        polygonXA.add(118.143555);
        polygonXA.add(118.183799);
        polygonXA.add(118.21082);
        polygonXA.add(118.191848);
        polygonXA.add(118.144705);
        polygonXA.add(118.080314);
        polygonXA.add(118.080889);
        polygonXA.add(118.092388);
        polygonYA.add(24.564185);
        polygonYA.add(24.546831);
        polygonYA.add(24.495282);
        polygonYA.add(24.466341);
        polygonYA.add(24.428972);
        polygonYA.add(24.458974);
        polygonYA.add(24.503173);
        polygonYA.add(24.530001);
        System.out.println(isPointInPolygon(px, py, polygonXA, polygonYA));
    }

    public static boolean isPointInPolygon(double px, double py, ArrayList<Double> polygonXA,
                                           ArrayList<Double> polygonYA) {
        boolean isInside = false;
        double ESP = 1e-9;
        int count = 0;
        double linePoint1x;
        double linePoint1y;
        double linePoint2x = 180;
        double linePoint2y;
        linePoint1x = px;
        linePoint1y = py;
        linePoint2y = py;
        for (int i = 0; i < polygonXA.size() - 1; i++) {
            double cx1 = polygonXA.get(i);
            double cy1 = polygonYA.get(i);
            double cx2 = polygonXA.get(i + 1);
            double cy2 = polygonYA.get(i + 1);
            if (isPointOnLine(px, py, cx1, cy1, cx2, cy2)) {
                return true;
            }
            if (Math.abs(cy2 - cy1) < ESP) {
                continue;
            }
            if (isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
                if (cy1 > cy2) {
                    count++;
                }
            } else if (isPointOnLine(cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
                if (cy2 > cy1) {
                    count++;
                }
            } else if (isIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y, linePoint2x, linePoint2y)) {
                {
                    count++;
                }
            }
        }
        System.out.println(count);
        if (count % 2 == 1) {
            isInside = true;
        }
        return isInside;
    }

    public static double Multiply(double px0, double py0, double px1, double py1, double px2, double py2) {
        return ((px1 - px0) * (py2 - py0) - (px2 - px0) * (py1 - py0));
    }

    public static boolean isPointOnLine(double px0, double py0, double px1, double py1, double px2, double py2) {
        boolean flag = false;
        double ESP = 1e-9;
        if ((Math.abs(Multiply(px0, py0, px1, py1, px2, py2)) < ESP) && ((px0 - px1) * (px0 - px2) <= 0)
                && ((py0 - py1) * (py0 - py2) <= 0)) {
            flag = true;
        }
        return flag;
    }

    public static boolean isIntersect(double px1, double py1, double px2, double py2, double px3, double py3,
                                      double px4, double py4) {
        boolean flag = false;
        double d = (px2 - px1) * (py4 - py3) - (py2 - py1) * (px4 - px3);
        if (d != 0) {
            double r = ((py1 - py3) * (px4 - px3) - (px1 - px3) * (py4 - py3)) / d;
            double s = ((py1 - py3) * (px2 - px1) - (px1 - px3) * (py2 - py1)) / d;
            if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1)) {
                flag = true;
            }
        }
        return flag;
    }
}
