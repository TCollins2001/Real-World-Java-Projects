const music = document.getElementById("bgMusic");
const toggle = document.getElementById("musicToggle");

const TARGET_VOLUME = 0.3;

let isMuted = localStorage.getItem("musicMuted");

if (isMuted === null) {
  isMuted = true;
  localStorage.setItem("musicMuted", "true");
} else {
  isMuted = isMuted === "true";
}
music.muted = isMuted;

toggle.src = isMuted
  ? "/images/music-off.png"
  : "/images/music-on.gif";

function fadeInMusic() {
  music.volume = 0;
  music.play().catch(() => {});

  let vol = 0;
  const fade = setInterval(() => {
    vol += 0.03;
    if (vol >= TARGET_VOLUME) {
      music.volume = TARGET_VOLUME;
      clearInterval(fade);
    } else {
      music.volume = vol;
    }
  }, 25);
}

function fadeOutMusic(callback) {
  if (music.muted || music.paused) {
    callback();
    return;
  }

  let vol = music.volume;
  const fade = setInterval(() => {
    vol -= 0.03;
    if (vol <= 0) {
      music.volume = 0;
      clearInterval(fade);
      callback();
    } else {
      music.volume = vol;
    }
  }, 25);
}

music.addEventListener("loadedmetadata", () => {
  const savedTime = parseFloat(localStorage.getItem("musicTime"));
  if (!isNaN(savedTime)) {
    music.currentTime = savedTime;
  }

  if (!isMuted) {
    fadeInMusic();
  }
});

setInterval(() => {
  if (!music.paused && !music.muted) {
    localStorage.setItem("musicTime", music.currentTime);
  }
}, 500);

toggle.addEventListener("click", (e) => {
  e.stopPropagation();

  isMuted = !isMuted;
  music.muted = isMuted;
  localStorage.setItem("musicMuted", isMuted);

  toggle.src = isMuted
    ? "/images/music-off.png"
    : "/images/music-on.gif";

  if (!isMuted) {
    fadeInMusic();
  }
});
