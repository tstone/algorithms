open Core.Std

type position_2d =
  { x :  float;
    y :  float;
  }

type circle =
  { r      : float;
    center : position_2d;
  }

type cluster =
  { members : circle list;
    center  : position_2d;
  }


(* hierarchitical clustering algorithm:
   - turn each circle into a cluster of 1
   - combine 2 nearest clusters into one cluster until at max *)
let reduce_circles_to_max circles max =
  let clusters = List.map circles ~f:(fun c -> cluster_from_circle c) in
  reduce_clusters_to_max clusters

let rec reduce_clusters_to_max clusters max =
  if List.size clusters <= max then clusters
  else let (cluster_a, cluster_b, remaining) = find_nearest_pair clusters in
    reduce_clusters_to_max(combine_cluster(cluster_a cluster_b) :: remaining)

(* this is getting messy, and is wrong... *)
let find_nearest_pair clusters =
  match clusters with
  | Nil | [_] | [_, _] => None
  | hd :: snd :: tl =>
    List.fold_left (hd :: snd :: tl) ~init:(hd, snd, Float.max_value, tl)
      ~f: fun acc c1 ->
        let (a, b, current_closest_dist, rem) = acc in
        let (_, opt_result) = List.findi rem (fun _ c2 ->
            (great_circle_distance_2d c1.center c2.center) < current_closest_dist) in
        match opt_result with
        | ???

let combine_cluster cluster1 cluster2 =
  let union_members = List.concat cluster1.members cluster2.members in
  let centroid = center_point List.map union_members ~f:(fun c -> c.center) in
  { members = union_members; center = centroid }

let center_point points =
  let avg_x = float_avg(List.map points ~f:(fun p -> p.x)) in
  let avg_y = float_avg(List.map points ~f:(fun p -> p.y)) in
  { x: avg_x; y: avg_y; }

let cluster_from_circle circle =
  { members = [circle]; center = circle.center; }




(* necessary math/geometry implementation but not part of the clustering impl --
   this would ideally come from a geom library, but I'm still an ocaml newbie :*)

let pi = 4.0 *. atan 1.0
let wgs_84_r = 6378137

let float_avg floats =
  let sum = List.fold_left floats ~init:0. ~f:(fun acc x -> acc +. x) in
  let len = float_of_int(List.length floats) in
  sum /. len

let degrees_to_radians deg =
  deg /. 180.0 *. pi

let great_circle_distance_2d p1 p2 =
  let lng1_r = degrees_to_radians p1.x in
  let lng2_r = degrees_to_radians p2.x in
  let lat1_r = degrees_to_radians p1.y in
  let lat2_r = degrees_to_radians p2.y in
  let a1 = sin(lat1_r) *. sin(lat2_r) +.
           cos(lat1_r) *. cos(lat2_r) *.
           cos(lng2_r -. lng1_r) in
  (float_of_int wgs_84_r) *. acos(min a1 1.)
