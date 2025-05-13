export interface GameDetails {
  id: number;
  slug: string;
  name: string;
  name_original: string;
  description: string;
  metacritic: null;
  metacritic_platforms: any[];
  released: string;
  tba: boolean;
  updated: string;
  background_image: string;
  background_image_additional: string;
  website: string;
  rating: number;
  rating_top: number;
  ratings: Rating[];
  reactions: null;
  added: number;
  added_by_status: Addedbystatus;
  playtime: number;
  screenshots_count: number;
  movies_count: number;
  creators_count: number;
  achievements_count: number;
  parent_achievements_count: number;
  reddit_url: string;
  reddit_name: string;
  reddit_description: string;
  reddit_logo: string;
  reddit_count: number;
  twitch_count: number;
  youtube_count: number;
  reviews_text_count: number;
  ratings_count: number;
  suggestions_count: number;
  alternative_names: any[];
  metacritic_url: string;
  parents_count: number;
  additions_count: number;
  game_series_count: number;
  user_game: null;
  reviews_count: number;
  saturated_color: string;
  dominant_color: string;
  parent_platforms: Parentplatform[];
  platforms: Platform3[];
  stores: Store2[];
  developers: Developer[];
  genres: Developer[];
  tags: Tag[];
  publishers: Developer[];
  esrb_rating: null;
  clip: null;
  description_raw: string;
}

interface Tag {
  id: number;
  name: string;
  slug: string;
  language: string;
  games_count: number;
  image_background: string;
}

interface Developer {
  id: number;
  name: string;
  slug: string;
  games_count: number;
  image_background: string;
}

interface Store2 {
  id: number;
  url: string;
  store: Store;
}

interface Store {
  id: number;
  name: string;
  slug: string;
  domain: string;
  games_count: number;
  image_background: string;
}

interface Platform3 {
  platform: Platform2;
  released_at: string;
  requirements: Requirements;
}

interface Requirements {
  minimum: string;
}

interface Platform2 {
  id: number;
  name: string;
  slug: string;
  image: null;
  year_end: null;
  year_start: null;
  games_count: number;
  image_background: string;
}

interface Parentplatform {
  platform: Platform;
}

interface Platform {
  id: number;
  name: string;
  slug: string;
}

interface Addedbystatus {
  yet: number;
  owned: number;
  beaten: number;
  toplay: number;
  dropped: number;
  playing: number;
}

interface Rating {
  id: number;
  title: string;
  count: number;
  percent: number;
}