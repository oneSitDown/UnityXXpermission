using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Test : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        AndroidJavaObject androidJavaObject = new AndroidJavaObject("com.demo.myapplication.unitypermission");
        androidJavaObject.Call("RequestPermission");
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
