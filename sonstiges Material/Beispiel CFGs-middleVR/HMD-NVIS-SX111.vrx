<?xml version="1.0" ?>
<MiddleVR>
    <Kernel LogLevel="2" EnableCrashHandler="0" Version="1.0.8.20121008" />
    <DeviceManager WandAxis="0" WandHorizontalAxis="0" WandHorizontalAxisScale="1" WandVerticalAxis="1" WandVerticalAxisScale="1" WandButtons="0" WandButton0="0" WandButton1="1" WandButton2="2" WandButton3="3" WandButton4="4" WandButton5="5">
        <Driver Type="vrDriverDirectInput" />
    </DeviceManager>
    <DisplayManager Fullscreen="0" WindowBorders="0" ShowMouseCursor="1" VSync="1" SaveRenderTarget="0">
        <Node3D Name="CenterNode" Parent="VRRootNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="HandNode" Tag="Hand" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="HeadNode" Tag="Head" Parent="CenterNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Node3D Name="NVIS-SX111" Parent="HeadNode" Tracker="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.000000,1.000000" />
        <Camera Name="CameraRight" Parent="NVIS-SX111" Tracker="0" PositionLocal="0.032500,0.000000,0.000000" OrientationLocal="0.000000,0.000000,-0.113203,0.993572" VerticalFOV="64" Near="0.1" Far="1000" Screen="0" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1.25" />
        <Camera Name="CameraLeft" Parent="NVIS-SX111" Tracker="0" PositionLocal="-0.032500,0.000000,0.000000" OrientationLocal="0.000000,0.000000,0.113203,0.993572" VerticalFOV="64" Near="0.1" Far="1000" Screen="0" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1.25" />
        <Viewport Name="ViewportLeft" Left="0" Top="0" Width="1280" Height="1024" Camera="CameraLeft" Stereo="0" StereoMode="3" CompressSideBySide="0" StereoInvertEyes="0" />
        <Viewport Name="ViewportRight" Left="1280" Top="0" Width="1280" Height="1024" Camera="CameraRight" Stereo="0" StereoMode="3" CompressSideBySide="0" StereoInvertEyes="0" />
    </DisplayManager>
    <ClusterManager NVidiaSwapLock="0" DisableVSyncOnServer="1" ForceOpenGLConversion="0" BigBarrier="0" SimulateClusterLag="0" />
</MiddleVR>
