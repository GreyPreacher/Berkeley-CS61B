import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    private static double[] LonDPP = new double[8];
    private static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
            ROOT_LRLAT = 37.82280243352756, ROOT_LRLON = -122.2119140625;
    static {
        LonDPP[0] = ( ROOT_LRLON - ROOT_ULLON ) / MapServer.TILE_SIZE;
        for (int i = 1; i < 8; ++i) {
            LonDPP[i] = LonDPP[i - 1] / 2;
        }
    }

    public Rasterer() {
        // YOUR CODE HERE
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        // System.out.println(params);
        Map<String, Object> results = new HashMap<>();

        double requestedULLon = params.get("ullon");
        double requestedULLat = params.get("ullat");
        double requestedLRLon = params.get("lrlon");
        double requestedLRLat = params.get("lrlat");

        if (requestedLRLat >= ROOT_ULLAT || requestedULLat <= ROOT_LRLAT ||
        requestedLRLon <= ROOT_ULLON || requestedULLon >= ROOT_LRLON ||
        requestedULLat >= requestedLRLat || requestedULLon >= requestedLRLon) {
            results.put("query_success", false);
            results.put("depth", 0);
            results.put("render_grid", null);
            results.put("raster_ul_lon", 0);
            results.put("raster_ul_lat", 0);
            results.put("raster_lr_lon", 0);
            results.put("raster_lr_lat", 0);
            return results;
        }

        double requestedLonDPP = (requestedLRLon - requestedULLon) / params.get("w");
        int depth = getDepth(requestedLonDPP);
        results.put("depth", depth);

        double maxLevel = Math.pow(2, depth);
        double xDiff = (ROOT_LRLON - ROOT_ULLON) / maxLevel;
        double yDiff = (ROOT_LRLAT - ROOT_ULLAT) / maxLevel;

        int xLeft = 0, xRight = 0, yLeft = 0, yRight = 0;
        for (double x = ROOT_ULLON; x <= ROOT_LRLON; x += xDiff) {
            if (x <= requestedULLon) ++xLeft;
            if (xRight < maxLevel && xRight <= requestedLRLon) ++xRight;
        }
        for (double y = ROOT_ULLAT; y >= ROOT_LRLAT; y -= yDiff) {
            if (y >= requestedULLat) ++yLeft;
            if (yRight < maxLevel && y <= requestedLRLat) ++yRight;
        }
        if (xLeft != 0) --xLeft;
        if (yLeft != 0) --yLeft;
        if (xRight != 0) --xRight;
        if (yRight != 0) --yRight;

        String[][] files = new String[yRight - yLeft + 1][xRight - xLeft + 1];
        for (int y = yLeft; y <= yRight; ++y) {
            for (int x = xLeft; x <= xRight; ++x) {
                files[y - yLeft][x - xLeft] = "d" + depth + "_x" + x + "_y" + y + ".png";
            }
        }
        results.put("query_success", true);
        results.put("render_grid", files);
        results.put("raster_ul_lon", ROOT_ULLON + xLeft * xDiff);
        results.put("raster_ul_lat", ROOT_ULLAT + yLeft * yDiff);
        results.put("raster_lr_lon", ROOT_LRLON + (xRight + 1) * xDiff);
        results.put("raster_lr_lat", ROOT_LRLAT + (yRight + 1) * yDiff);
        return results;
    }

    private int getDepth(double requestedLonDPP) {
        int depth = 0;
        while (requestedLonDPP < LonDPP[depth]) {
            depth++;
            if (depth == LonDPP.length - 1) break;
        }
        return depth;
    }

}
