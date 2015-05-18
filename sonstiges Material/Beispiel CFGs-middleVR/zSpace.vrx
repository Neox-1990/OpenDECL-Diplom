<?xml version="1.0" ?>
<MiddleVR>
    <Kernel LogLevel="2" LogInSimulationFolder="0" EnableCrashHandler="0" Version="1.4.0.f2" />
    <DeviceManager WandAxis="0" WandHorizontalAxis="0" WandHorizontalAxisScale="1" WandVerticalAxis="1" WandVerticalAxisScale="1" WandButtons="zSpace.Stylus.Buttons" WandButton0="0" WandButton1="1" WandButton2="2" WandButton3="3" WandButton4="4" WandButton5="5">
        <Driver Type="vrDriverDirectInput" />
        <Driver Type="vrDriverZSpace" StylusLEDColor="0.000000,0.000000,0.000000" StylusLEDTurnedOn="0" DefaultDurationVibration="0.15" DefaultDurationBetween="0.02" DefaultNbVibrations="2" />
    </DeviceManager>
    <DisplayManager Fullscreen="0" WindowBorders="0" ShowMouseCursor="1" VSync="0" AntiAliasing="0" ForceHideTaskbar="0" SaveRenderTarget="0">
        <Node3D Name="CenterNode" Parent="VRRootNode" Tracker="TrackerSimulatorMouse0.Tracker" IsFiltered="0" UseTrackerX="1" UseTrackerY="1" UseTrackerZ="1" UseTrackerYaw="1" UseTrackerPitch="1" UseTrackerRoll="1" />
        <Screen Name="Screen0" Parent="CenterNode" Tracker="0" IsFiltered="0" PositionLocal="0.000000,0.000000,0.000000" OrientationLocal="-0.500000,0.000000,0.000000,0.866025" Width="0.523" Height="0.292" />
        <Node3D Name="HeadNode" Parent="CenterNode" Tracker="zSpace.Head.Tracker" IsFiltered="0" UseTrackerX="1" UseTrackerY="1" UseTrackerZ="1" UseTrackerYaw="1" UseTrackerPitch="1" UseTrackerRoll="1" />
        <CameraStereo Name="CameraStereo0" Parent="HeadNode" Tracker="0" IsFiltered="0" PositionLocal="0.000000,0.000000,0.000000" VerticalFOV="60" Near="0.1" Far="1000" Screen="Screen0" ScreenDistance="1" UseViewportAspectRatio="0" AspectRatio="1.7911" InterEyeDistance="0.065" LinkConvergence="1" />
        <Node3D Name="HandNode" Tag="Hand" Parent="CenterNode" Tracker="zSpace.Stylus.Tracker" IsFiltered="0" UseTrackerX="1" UseTrackerY="1" UseTrackerZ="1" UseTrackerYaw="1" UseTrackerPitch="1" UseTrackerRoll="1" />
        <Viewport Name="Viewport0" Left="1920" Top="0" Width="1920" Height="1080" Camera="CameraStereo0" Stereo="1" StereoMode="0" CompressSideBySide="0" StereoInvertEyes="0" OculusRiftWarping="0" UseHomography="0" />
    </DisplayManager>
    <Scripts>
        <Script Type="TrackerSimulatorMouse" Name="TrackerSimulatorMouse0" />
    </Scripts>
    <ClusterManager NVidiaSwapLock="0" DisableVSyncOnServer="1" ForceOpenGLConversion="0" BigBarrier="0" SimulateClusterLag="0" />
</MiddleVR>
