/**
 * 捷宇摄像头操作(D版)
 * @author zhanghj
 */
function JyCamera(camId) {
    this.id = document.getElementById(camId);
}

//window.onerror =
//function(errorMessage, scriptURI, lineNumber) {
//    catchError({
//        message: errorMessage,
//        script: scriptURI,
//        line: lineNumber
//    });
//};
//
//
//function catchError(str) {
//    var obj = eval('(' + str + ')');
//}

JyCamera.prototype = {
        /**
         * 打开主摄像头
         * @param remote 旋转角度
         */
        openMainCamera : function(remote) {
            var arguLength = arguments.length;
            if (arguLength ==0 || remote == null || remote == undefined) {
                remote = 270;
            }
            this.stopCamera();
            this.setResolution(7);
            return this.id.bStartPlayRotate(remote);
        },
        /**
         * 打开副摄像头
         * @param remote 旋转角度
         */
        openCamera2 : function(remote) {
            this.stopCamera();
            var rel = false;
            try {
                rel = this.id.bStartPlay2(remote);
            } catch (error) {
                return false;
            }
        },
        /**
         * 暂停视频源
         */
        pauseCamera : function() {
            return this.id.bPausePlay();
        },
        /**
         * 停止视频源
         */
        stopCamera : function() {
            return this.id.bStopPlay();
        },
        /**
         * 设置视频分辨率
         * @param resolutionType
         * <pre>
         *     1—320*240
         *     2—640*480
         *     3—800*600
         *     4—1024*768
         *     5—1600*1200
         *     6—2048*1536
         *     7—2592*1944
         *     0/其它—按设备默认值分辨率
         * </pre>
         */
        setResolution : function(resolutionType) {
            if (resolutionType == null || resolutionType == undefined) {
                throw new Error("The resolutionType is not null.");
            }
            return this.id.vSetResolution(resolutionType);
        },
        /**
         * 放大
         */
        zoomOut : function() {
            this.id.vZoomOut();
        },
        /**
         * 缩小
         */
        zoomIn : function() {
            this.id.vZoomIn();
        },
        /**
         * 还原
         */
        zoomOriginal : function() {
            this.id.vZoomOriginal();
        },
        /**
         * 参数设置
         */
        setDisplayVideoPara : function() {
            this.id.displayVideoPara();
        },
        /**
         * DPI设置
         * @param x 横向，默认96
         * @param y 纵向，默认96
         */
        setDPI : function(x, y) {
            this.id.vSetDPI(x, y);
        },
        /**
         * 获取DPI
         * @param x 横向，默认96
         * @param y 纵向，默认96
         */
        getDPI : function(x, y) {
            return this.id.vGetDPI(x, y);
        },
        /**
         * 设置当前旋转角度
         * @param remote
         */
        setRemote : function(remote) {
            this.id.vSetRotate(remote);
        },
        /**
         * 获取当前旋转角度
         */
        getRemote : function() {
            return this.id.iGetRotate();
        },
        /**
         * 设置拍照模式
         * @param mode
         * <pre>
         *    0—支持鼠标框选模式（ 默认模式）
         *    1—定义固定大小拍照模式
         *    2—定义固定大小身份证拍照模式
         *    3—自动寻边
         *    4—自动寻边身份证拍照模式
         * </pre>
         * @returns
         */
        setMode : function(mode) {
            return this.id.bSetMode(mode);
        },
        /**
         * 设置拍照区域大小(宽和高分为 10000 份)
         * <p>调用该接口前要先调用 bSetMode(1)函数， 设置为 1 模式</p>
         * @param left:1-1000
         * @param top:1-1000
         * @param right:1-1000
         * @param bottom:1-1000
         * @returns
         */
        setImageArea : function(left, top, right, bottom) {
            return this.id.bSetImageArea(left, top, right, bottom);
        },
        /**
         * 获取拍照区域大小
         */
        getImageArea : function() {
            var left = this.id.lGetImageAreaX1();
            var top = this.id.lGetImageAreaY1();
            var right = this.id.lGetImageAreaX2();
            var bottom = this.id.lGetImageAreaY2();

            return {"left" : left, "top" : top, "right" : right, "bottom" : bottom};
        },
        /**
         * 设置图片宽高分辨率的缩放率
         * @param fImageXYZoom 宽高分辨率的缩放率（ 默认值为 1.0）
         */
        setIamgeXYZoom : function(fImageXYZoom) {
            return this.id.bSetIamgeXYZoom(fImageXYZoom);
        },
        /**
         * 设置图片保存的压缩率 (拍.BMP 图片本函数无效)
         * @param sImageQuality 图片保存的压缩率（ 区间： 1－100， 默认值： 70）
         */
        setImageQuality : function(sImageQuality) {
            this.id.vSetImageQuality(sImageQuality);
        },
        /**
         * 获取图片保存的压缩率（ 区间： 1－100）
         */
        getImageQuality : function() {
            return this.id.sGetImageQuality();
        },
        /**
         * 自动矫正
         * @param flag true, false
         */
        setSkewFlag : function(flag) {
            this.id.vSetSkewFlag(lag);
        },
        /**
         * 设置黑边去除
         * @param flag true, false
         */
        setDelHBFlag : function(flag) {
            this.id.vSetDelHBFlag(lag);
        },
        /**
         * 设置曝光相对值
         * @param iExposure：0－100
         */
        setExposure : function(iExposure) {
            this.id.vSetExposure(iExposure);
        },
        /**
         * 保存图片
         * @param filePath 存储路径:保存多个文件时，格式为数组
         * @param fileName 存储名字:保存多个文件时，格式为数组
         * @param type 图片类型 0:BMP,1:JPG,2:PNG,3:TIF,4:多页黑白TIF,5:多页彩色TIF,
         *                   6:灰度图和TIF图片(filePath[0]:灰度图片路径，fileName[0]:灰度图片名称；filePath[1]:TIF图片路径，fileName[1]:TIF图片名称)
         *                   7:彩色图和黑白图片(filePath[0]:彩色图片路径，fileName[0]:彩色图片名称；filePath[1]:黑白图片路径，fileName[1]:黑白图片名称)
         *                   8:保存为 TIF 并进行 OCR 识别,9:灰度图,10:黑白 JPG 图片
         * @param muiltPages 0:单页,1:多页
         * @param bit 灰度图位数 8或者24
         */
        savePicture : function(filePath, fileName, type, muiltPages, bit) {
            var result = false;
            switch (type) {
            case 0:// BMP
                result = this.id.bSaveBMP(filePath, fileName);
                break;
            case 1:// JPG
                result = this.id.bSaveJPG(filePath, fileName);
                break;
            case 2:// PNG
                result = this.id.bSavePNG(filePath, fileName);
                break;
            case 3:// TIF
                result = this.id.bSaveTIF(filePath, fileName);
                break;
            case 4:// 多页黑白TIF
                if (muiltPages == null || muiltPages == undefined) {
                    muiltPages = 0;
                }
                result = this.id.bSaveTIFEx(filePath, fileName, muiltPages);
                break;
            case 5:// 多页彩色TIF
                if (muiltPages == null || muiltPages == undefined) {
                    muiltPages = 0;
                }
                result = this.id.bSaveTIF24Bit(filePath, fileName, muiltPages);
                break;
            case 6:// 灰度图和TIF图片
                result = this.id.bSaveGrayAndTIF(filePath[0], fileName[0], filePath[1], fileName[1]);
                break;
            case 7:// 彩色图和黑白图片
                result = this.id.bSaveJpgAndTIF(filePath[0], fileName[0], filePath[1], fileName[1]);
                break;
            case 8:// 保存为 TIF 并进行 OCR 识别
                result = this.id.bSaveGray(filePath, fileName);
                break;
            case 9:// 灰度图
                if (bit == null || bit == undefined) {
                    bit = 8;
                }
                result = this.id.bSaveGrayEx(filePath, fileName, bit);
                break;
            case 10:// 黑白JPG
                result = this.id.bSaveTifJPG(filePath, fileName);
                break;

            default:
                break;
            }
        },
        /**
         * 获取指定类型的base64图片
         * @param imageType
         *        1—BMP 11 灰度 BMP 21 黑白 BMP
                  2—GIF 12 灰度 GIF 22 黑白 GIF
                  3—JPG 13 灰度 JPG 23 黑白 JPG
                  4—PNG 14 灰度 PNG 24 黑白 PNG
                  5—TIF（ 黑白） 51 灰度 TFI 52 彩色 TIF
                  其它—JPG
         * @returns
         */
        getBase64ForType : function(imageType) {
            return this.id.sGetImageBase64(imageType);
        },
        /**
         * base64转文件
         * @param filePath
         * @param base64
         */
        base64Decode : function(filePath, base64) {
            return this.id.Base64Decode(filePath, base64);
        },
        /**
         * 图片旋转
         * @param filePath 文件路径
         * @param remote
         */
        rotateJpgImage : function(filePath, remote) {
            this.id.bRotateJpgImage(filePath, remote);
        },
        /**
         * ocr识别并保存文档,例如：ocrImage ("D:\\A4.jpg",2,2,"D:\\A4.doc");
         * @param fileName 图片所在位置
         * @param lang 文字类型，1:英文，2:中文
         * @param fileType 保存文件类型， 1： TXT， 2： DOC， 3： XLS
         * @param filePath 文件保存位置
         */
        ocrImage : function(fileName, lang, fileType, filePath) {
        	this.id.bOCRImage(fileName, lang, fileType, filePath);
        },
        /**
         * 识别成字符串
         * @param fileName
         * @param lang
         */
        ocrImageToString : function(fileName, lang) {
        	return this.id.sOCRImageToString(fileName, lang);
        },
        upload : function(file, serverName, usPort, objectName, bWaitUI, bRetUI) {
        	return this.id.sUpLoadImageEx2(file, serverName, usPort, objectName, bWaitUI, bRetUI);
        },
        /**
         * 读身份证卡内信息(-1:端口初始化失败 -2:卡认证失败（请重新将卡放到读卡器） -3:读取数据失败 -4: 生成相片文件失败（请检查设定路径和磁盘空间） 1:正确)
         * @parma port 对应的设备端口，USB型为1001，串口型为1至16
         */
        ocrIdCard: function(port, phontoPath) {
        	return this.id.ReadCard(port, phontoPath);
        },
        /**
         * 获得身份证个人身份证号信息
         */
        getIdCardNumber: function() {
        	return this.id.GetCode();
        }
}


