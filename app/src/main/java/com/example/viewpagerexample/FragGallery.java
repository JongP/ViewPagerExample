package com.example.viewpagerexample;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FragGallery extends Fragment {
    private View view;
    private Button add;
    private ImageView img1;
    private ImageView img2;
    private Integer cnt=0;

    String imgName = "osz.png";    // 이미지 이름


    //상태 저장하기
    public static FragGallery newInstance() {
        FragGallery fragGallery = new FragGallery();
        return fragGallery;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_gallery, container, false);

        add = (Button) view.findViewById(R.id.getGallery);
        img1 = (ImageView) view.findViewById(R.id.imageView);
        img2 = (ImageView) view.findViewById(R.id.imageView2);


        try {
            String imgpath = getActivity().getCacheDir() + "/" + cnt;   // 내부 저장소에 저장되어 있는 이미지 경로
            Bitmap bm = BitmapFactory.decodeFile(imgpath);
            img2.setImageBitmap(bm);   // 내부 저장소에 저장된 이미지를 이미지뷰에 셋

            Toast.makeText(getContext(), "파일 로드 성공", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "파일 로드 실패", Toast.LENGTH_SHORT).show();
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { // 갤러리
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == getActivity().RESULT_OK) {
                //ClipData 또는 Uri를 가져온다
                Uri uri = data.getData();
                ClipData clipData = data.getClipData();

                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if(clipData!=null)
                {

                    for(int i = 0; i < 3; i++)
                    {
                        if(i<clipData.getItemCount()){
                            ContentResolver resolver = getActivity().getContentResolver();
                            try {
                                Uri urione =  clipData.getItemAt(i).getUri();
                                InputStream instream = resolver.openInputStream(urione);
                                Bitmap imgBitmap = BitmapFactory.decodeStream(instream);

                                switch (i){
                                    case 0:
                                        img1.setImageBitmap(imgBitmap);
                                        break;
                                    case 1:
                                        img2.setImageBitmap(imgBitmap);
                                        break;
                                    case 2:
                                        img2.setImageBitmap(imgBitmap);
                                        break;
                                }
                                instream.close();   // 스트림 닫아주기
                                saveBitmapToJpeg(imgBitmap);    // 내부 저장소에 저장
                                Toast.makeText(getContext(), "파일 불러오기 성공", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(getContext(), "파일 불러오기 실패", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
                else if(uri != null)
                {
                    ContentResolver resolver = getActivity().getContentResolver();
                    try {
                        InputStream instream = resolver.openInputStream(uri);
                        Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                        img1.setImageBitmap(imgBitmap);    // 선택한 이미지 이미지뷰에 셋
                        instream.close();   // 스트림 닫아주기
                        saveBitmapToJpeg(imgBitmap);    // 내부 저장소에 저장
                        Toast.makeText(getContext(), "파일 불러오기 성공", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "파일 불러오기 실패", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }
    }

    public void saveBitmapToJpeg(Bitmap bitmap) {   // 선택한 이미지 내부 저장소에 저장
        File tempFile = new File(getActivity().getCacheDir(), cnt.toString());    // 파일 경로와 이름 넣기
        try {
            tempFile.createNewFile();   // 자동으로 빈 파일을 생성하기
            FileOutputStream out = new FileOutputStream(tempFile);  // 파일을 쓸 수 있는 스트림을 준비하기
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);   // compress 함수를 사용해 스트림에 비트맵을 저장하기
            out.close();    // 스트림 닫아주기
            cnt++;
            Toast.makeText(getContext(), "파일 저장 성공", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "파일 저장 실패", Toast.LENGTH_SHORT).show();
        }
    }
}

