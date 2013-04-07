<?php
$app_id = "496562937069311";
$app_secret = "feaa80f2fd8e03855d72f1d9eaeb213d";
$my_url = "YOUR_POST_LOGIN_URL";
$video_title = "TITLE FOR THE VIDEO";
$video_desc = "DESCRIPTION FOR THE VIDEO";
$page_id = "159375647558829"; // Set this to your APP_ID for Applications

$code = $_REQUEST["code"];

echo '<html><body>';

if(empty($code)) {
  // Get permission from the user to publish to their page. 
  $dialog_url = "http://www.facebook.com/dialog/oauth?client_id="
    . $app_id . "&redirect_uri=" . urlencode($my_url)
    . "&scope=publish_stream,manage_pages";
  echo('<script>top.location.href="' . $dialog_url . '";</script>');
} else {

  // Get access token for the user, so we can GET /me/accounts
  $token_url = "https://graph.facebook.com/oauth/access_token?client_id="
      . $app_id . "&redirect_uri=" . urlencode($my_url)
      . "&client_secret=" . $app_secret
      . "&code=" . $code;
  $access_token = file_get_contents($token_url);

  $accounts_url = "https://graph.facebook.com/me/accounts?" . $access_token;
  $response = file_get_contents($accounts_url);

  // Parse the return value and get the array of accounts we have
  // access to. This is returned in the data[] array. 
  $resp_obj = json_decode($response,true);
  $accounts = $resp_obj['data'];

  // Find the access token for the page to which we want to post the video.
  foreach($accounts as $account) {
       if($account['id'] == $page_id) {
         $access_token = $account['access_token'];
         break;
       }
  }

  // Post feed
  $post_url = 'https://graph.facebook.com/'.$page_id.'/feed?message='.urlencode('Testing').'&access_token='.$access_token.'&method=post';

  // Post photo
  $photo_url = 'http://' . $_SERVER['HTTP_HOST'] . '/phd4/img/crimetape.jpg';
  $post_url = 'https://graph.facebook.com/'.$page_id.'/photos?message='.urlencode('Testing photo').'&url='.$photo_url.'&access_token='.$access_token.'&method=post';

  // Using the page access token from above, create the POST action
  // that our form will use to upload the video.
//  $video_url = 'http://' . $_SERVER['HTTP_HOST'] . '/phd4/img/crimetape.mp4';
//  $post_url = "https://graph-video.facebook.com/" . $page_id . "/videos?"
//      . "title=" . $video_title. "&description=" . $video_desc
//      . "&source=". $video_url
//      . "&access_token=". $access_token;

  // curl call
  $curl = curl_init();
  curl_setopt($curl, CURLOPT_RETURNTRANSFER, TRUE);
  curl_setopt($curl, CURLOPT_URL, $post_url);
  $outputJSON = curl_exec($curl);
  curl_close($curl);
  if(!$outputJSON) {
    echo curl_error($curl);
    exit;
  }
  exit;

  // Create a simple form 
//  echo '<form enctype="multipart/form-data" action=" '.$post_url.' "  
//       method="POST">';
//  echo 'Please choose a file:';
//  echo '<input name="file" type="file">';
//  echo '<input type="submit" value="Upload" />';
//  echo '</form>';
}

echo '</body></html>';
?>