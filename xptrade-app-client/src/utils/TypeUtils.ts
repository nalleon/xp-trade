
/**
 * RAWGAPI types
 */
export interface RootObject {
  count: number;
  next: string;
  previous: null;
  results: Result[];
  seo_title: string;
  seo_description: string;
  seo_keywords: string;
  seo_h1: string;
  noindex: boolean;
  nofollow: boolean;
  description: string;
  filters: Filters;
  nofollow_collections: string[];
}

export interface Filters {
  years: Year2[];
}

export interface Year2 {
  from: number;
  to: number;
  filter: string;
  decade: number;
  years: Year[];
  nofollow: boolean;
  count: number;
}

export interface Year {
  year: number;
  count: number;
  nofollow: boolean;
}

export interface Result {
  id: number;
  slug: string;
  name: string;
  released: string;
  tba: boolean;
  background_image: string;
  rating: number;
  rating_top: number;
  ratings: Rating[];
  ratings_count: number;
  reviews_text_count: number;
  added: number;
  added_by_status: Addedbystatus;
  metacritic: number;
  playtime: number;
  suggestions_count: number;
  updated: string;
  user_game: null;
  reviews_count: number;
  saturated_color: string;
  dominant_color: string;
  platforms: Platform2[];
  parent_platforms: Parentplatform[];
  genres: Genre[];
  stores: Store2[];
  clip: null;
  tags: Tag[];
  esrb_rating: Platform3;
  short_screenshots: Shortscreenshot[];
}

export interface Shortscreenshot {
  id: number;
  image: string;
}

export interface Tag {
  id: number;
  name: string;
  slug: string;
  language: string;
  games_count: number;
  image_background: string;
}

export interface Store2 {
  id: number;
  store: Store;
}

export interface Store {
  id: number;
  name: string;
  slug: string;
  domain: string;
  games_count: number;
  image_background: string;
}

export interface Genre {
  id: number;
  name: string;
  slug: string;
  games_count: number;
  image_background: string;
}

export interface Parentplatform {
  platform: Platform3;
}

export interface Platform3 {
  id: number;
  name: string;
  slug: string;
}

export interface Platform2 {
  platform: Platform;
  released_at: string;
  requirements_en: Requirementsen | Requirementsen2 | Requirementsen3 | null | null | null;
  requirements_ru: Requirementsen | null | null;
}

export interface Requirementsen3 {
  minimum: string;
  recommended?: string;
}

export interface Requirementsen2 {
  minimum: string;
}

export interface Requirementsen {
  minimum: string;
  recommended: string;
}

export interface Platform {
  id: number;
  name: string;
  slug: string;
  image: null;
  year_end: null;
  year_start: null | null | number;
  games_count: number;
  image_background: string;
}

export interface Addedbystatus {
  yet: number;
  owned: number;
  beaten: number;
  toplay: number;
  dropped: number;
  playing: number;
}

export interface Rating {
  id: number;
  title: string;
  count: number;
  percent: number;
}

export interface Comment {
  id: number,
  id_post: number,
  username: string,
  content: string,
  creation_date: Date
}

 interface ScreenshootArr {
  count: number;
  next: null;
  previous: null;
  results: Screenshot[];
}

export interface Screenshot {
  id: number;
  image: string;
  width: number;
  height: number;
  is_deleted: boolean;
}

/**
 * Own types
 */
export interface XPTradeInputGame {
  game: {
    title: string;
    coverArt: string;
    developerInputDTOSet: {
      name: string;
    }[];
    genreInputDTOSet: {
      name: string;
    }[];
    platformInputDTOSet: {
      name: string;
    }[];
    publisherInputDTOSet: {
      name: string;
    }[];
    regionInputDTOSet: {
      name: string;
    }[];
  };
  user: {
    username: string;
    profilePicture: string;
  };
}

export interface XPTradeInputGameCollection {
  game: {
    title: string;
    coverArt: string;
    developerInputDTOSet: {
      name: string;
    }[];
    genreInputDTOSet: {
      name: string;
    }[];
    platformInputDTOSet: {
      name: string;
    }[];
    publisherInputDTOSet: {
      name: string;
    }[];
    regionInputDTOSet: {
      name: string;
    }[];
  };
  region: {
    name: string;
  };
  platform: {
    name: string;
  }
}

export interface PostXPTrade {
  id: number;
  game: GameOutputXPTrade;
  user: User;
  content: string;
  picture: null;
  creationDate: string;
}

interface User {
  username: string;
  profilePicture: null;
}

interface GameOutputXPTrade {
  id: number;
  title: string;
  coverArt: string;
  developerOutputDTOSet: DeveloperOutputDTOSet[];
  genreOutputDTOSet: DeveloperOutputDTOSet[];
  platformOutputDTOSet: DeveloperOutputDTOSet[];
  publisherOutputDTOSet: DeveloperOutputDTOSet[];
  regionOutputDTOSet: DeveloperOutputDTOSet[];
}

interface DeveloperOutputDTOSet {
  id: number;
  name: string;
}