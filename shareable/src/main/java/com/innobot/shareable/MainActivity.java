package com.innobot.shareable;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import com.facebook.share.widget.ShareDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_ShareTextUrl, btn_ShareImage;
    ShareDialog shareDialog;
    String thumnaliurl = "https://s3.amazonaws.com/cyrano-pub/user/8c2b7cd4-9800-8265-c85f-b1ed19d2804e.jpeg ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_ShareTextUrl = (Button) findViewById(R.id.btn_ShareTextUrl);
        btn_ShareImage = (Button) findViewById(R.id.btn_ShareImage);
        btn_ShareTextUrl.setOnClickListener(this);
        btn_ShareImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_ShareTextUrl:
                //  shareTextUrl();
                //shareparticularApp();
                shareProgram();
                break;

            case R.id.btn_ShareImage:
                //    shareImageParticularApp();
                //   shareImage();
                break;

            default:
                break;
        }

    }


    // Method to share either text or URL.
    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
        share.putExtra(Intent.EXTRA_TEXT, "http://www.codeofaninja.com");

        startActivity(Intent.createChooser(share, "Share link!"));
    }


    // Method to share any image.
    private void shareImage() {
        Intent share = new Intent(Intent.ACTION_SEND);

        // If you want to share a png image only, you can do:
        // setType("image/png"); OR for jpeg: setType("image/jpeg");
        share.setType("image/*");

        // Make sure you put example png image named myImage.png in your
        // directory
        String imagePath = Environment.getExternalStorageDirectory()
                + "/myImage.png";

        File imageFileToShare = new File(imagePath);

        Uri uri = Uri.fromFile(imageFileToShare);
        share.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(share, "Share Image!"));
    }


//    // Method to share any image.
//    private void shareImageParticularApp() {
//
//        Resources Image = getResources();
//        Intent share = new Intent(Intent.ACTION_SEND);
//
//        // If you want to share a png image only, you can do:
//        // setType("image/png"); OR for jpeg: setType("image/jpeg");
//        share.setType("image/*");
//
//        PackageManager pm = getPackageManager();
//        Intent SendImageIntent = new Intent(Intent.ACTION_SEND);
//        SendImageIntent.setType("image/*");
//
//        String imagePath = getString(R.string.share_email_gmail);
//
//        File imageFileToShare = new File(imagePath);
//
//        Uri uri = Uri.fromFile(imageFileToShare);
//            share.putExtra(Intent.EXTRA_STREAM, "https://cyrano-data-dev.s3.amazonaws.com/content/thumbnail/f347533b-2993-44b5-7538-02ed5267f1b8-00002.png?AWSAccessKeyId=AKIAJ5DUXSEFFZYJWY5Q&Expires=1502269540&Signature=2WvUZK32N22GDFDX7VnEk6xKLHQ%3D");
//
//
//        Intent openInChooser = Intent.createChooser(share, Image.getString(R.string.share_chooser_text));
//
//        List<ResolveInfo> resInfo = pm.queryIntentActivities(SendImageIntent, 0);
//        List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
//
//        for (int i = 0; i < resInfo.size(); i++) {
//            // Extract the label, append it, and repackage it in a LabeledIntent
//            ResolveInfo ri = resInfo.get(i);
//            String packageName = ri.activityInfo.packageName;
//            if (packageName.contains("android.email")) {
//                share.setPackage(packageName);
//            } else if (packageName.contains("twitter") || packageName.contains("facebook")||  packageName.contains("whatsapp") || packageName.contains("linkedin") || packageName.contains("email") || packageName.contains("android.gm")|| packageName.contains("com.google.android.apps.docs")) {
//                Intent intent = new Intent();
//                intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
//                intent.setAction(Intent.ACTION_SEND);
//                intent.setType("text/plain/");
//                if (packageName.contains("twitter")) {
//                    intent.putExtra(Intent.EXTRA_STREAM, uri);
//                } else if (packageName.contains("facebook")) {
//                    // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
//                    // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
//                    // will show the <meta content ="..."> text from that page with our link in Facebook.
//                    intent.putExtra(Intent.EXTRA_TEXT, Image.getString(R.string.share_programid));
//                    intent.putExtra(Intent.EXTRA_STREAM, uri);
//                } else if (packageName.contains("whatsapp")) {
//                    intent.putExtra(Intent.EXTRA_TEXT, Image.getString(R.string.share_programid));
//                    intent.putExtra(Intent.EXTRA_STREAM, uri);
//                } else if (packageName.contains("linkedin")) {
//                    intent.putExtra(Intent.EXTRA_TEXT, Image.getString(R.string.share_programid));
//                    intent.putExtra(Intent.EXTRA_STREAM, uri);
//                }else if (packageName.contains("com.google.android.apps.docs")) {
//
//                    intent.putExtra(Intent.EXTRA_TEXT, Image.getString(R.string.share_programid));
//                    intent.putExtra(Intent.EXTRA_STREAM, uri);
//                }
//
//                else if (packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
//                    intent.putExtra(Intent.EXTRA_STREAM, uri);
//                    intent.putExtra(Intent.EXTRA_SUBJECT, Image.getString(R.string.share_programid));
//                    intent.setType("message/rfc822");
//                }
//
//                intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
//
//
//            }
//        }
//
//        // convert intentList to array
//        LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
//        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
//        startActivity(openInChooser);
//
//    }



    private void shareProgram() {
        Resources resources = getApplicationContext().getResources();
        Intent emailIntent = new Intent();
        emailIntent.setAction(Intent.ACTION_SEND);
        // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
//        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(resources.getString(R.string.share_email_native)));
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.share_email_subject));
        emailIntent.setType("text/plain");
        PackageManager pm = getApplicationContext().getPackageManager();
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        Intent openInChooser = Intent.createChooser(emailIntent, "Choose");
        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
        List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
        for (int i = 0; i < resInfo.size(); i++) {
            // Extract the label, append it, and repackage it in a LabeledIntent
            ResolveInfo ri = resInfo.get(i);
            String packageName = ri.activityInfo.packageName;
            if (packageName.contains("twitter")) {
                emailIntent.setPackage(packageName);
            } else if (packageName.contains("twitter") || packageName.contains("facebook") ||
                    packageName.contains("whatsapp") || packageName.contains("linkedin") ||
                    packageName.contains("com.google.android.apps.docs") || packageName.contains("com.Slack")) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                //intent.setType("text/plain/image");
                if (packageName.contains("twitter")) {
                    intent.putExtra(Intent.EXTRA_TEXT, "Hello");
                } else if (packageName.contains("facebook")) {
                    // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                    // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                    // will show the <meta content ="..."> text from that page with our link in Facebook.
                    intent.putExtra(Intent.EXTRA_TEXT, "Hello");
                } else if (packageName.contains("whatsapp")) {
                    intent.putExtra(Intent.EXTRA_TEXT, "Hello");
                } else if (packageName.contains("linkedin")) {
                    intent.putExtra(Intent.EXTRA_TEXT, "Hello");
                } else if (packageName.contains("com.google.android.apps.docs")) {
                    intent.putExtra(Intent.EXTRA_TEXT, "Hello");
                }
               /* else if (packageName.contains("android.gm")) {
                    // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
//                    intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(resources.getString(R.string.share_email_gmail)));
//                    intent.putExtra(Intent.EXTRA_SUBJECT, sharedURL);
//                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_TEXT, "Hello");
                    *//*Uri uri = Uri.parse(publicThumbnail);
                    intent.putExtra(Intent.EXTRA_STREAM, uri);*//*
                } */
                else if (packageName.contains("com.Slack")) {
                    intent.putExtra(Intent.EXTRA_TEXT, "Hello");
                }
                intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
            }
        }
        // convert intentList to array
        LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
        startActivity(openInChooser);
    }





    private void shareparticularApp() {
        Resources resources = getResources();


        Intent emailIntent = new Intent();
        emailIntent.setAction(Intent.ACTION_SEND);
        // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(resources.getString(R.string.share_email_native)));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.share_email_subject));
        emailIntent.setType("message/rfc822");

        PackageManager pm = getPackageManager();
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");


        Intent openInChooser = Intent.createChooser(emailIntent, resources.getString(R.string.share_chooser_text));

        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
        List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
        for (int i = 0; i < resInfo.size(); i++) {
            // Extract the label, append it, and repackage it in a LabeledIntent
            ResolveInfo ri = resInfo.get(i);
            String packageName = ri.activityInfo.packageName;
            if (packageName.contains("android.email")) {
                emailIntent.setPackage(packageName);
            } else if (packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("whatsapp") || packageName.contains("linkedin") || packageName.contains("email") || packageName.contains("android.gm") || packageName.contains("com.google.android.apps.docs")) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                if (packageName.contains("twitter")) {
                    intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_link));
                    intent.putExtra(Intent.EXTRA_STREAM, resources.getString(R.string.share_imageurl));
                } else if (packageName.contains("facebook")) {
                    // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                    // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                    // will show the <meta content ="..."> text from that page with our link in Facebook.
                    //      intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_link));

                    // intialize facebook shareDialog.


//                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                            .setContentTitle("How to integrate Linkedin from your app")
//                            .setImageUrl(Uri.parse("https://www.numetriclabz.com/wp-content/uploads/2015/11/114.png"))
//                            .setContentDescription(
//                                    "simple LinkedIn integration")
//                            .setContentUrl(Uri.parse("https://www.numetriclabz.com/android-linkedin-integration-login-tutorial/"))
//                            .build();

                    //  intent.putExtra(Intent.EXTRA_TEXT,linkContent);

                } else if (packageName.contains("whatsapp")) {
                    intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_link));
                    intent.putExtra(Intent.EXTRA_STREAM, resources.getString(R.string.share_imageurl));
                } else if (packageName.contains("linkedin")) {
                    intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_link));
                } else if (packageName.contains("com.google.android.apps.docs")) {
                    System.out.println("package name " + packageName.contains("com.google.android.apps.docs"));
                    intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_link));
                } else if (packageName.contains("android.gm")) {
                    // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
//                    intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(resources.getString(R.string.share_email_gmail)));
                    intent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.share_email_subject));
//                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_link));

                    intent.putExtra(Intent.EXTRA_STREAM, resources.getString(R.string.share_link));
                }

                intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
            }
        }

        // convert intentList to array
        LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);

        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
        startActivity(openInChooser);




    }

}
